using System;
using System.Collections.Generic;
using System.Runtime.CompilerServices;
using System.Text;

namespace CommonLib
{
    internal sealed class RedBlackTreeNode<TValue>
    {
        public enum ColorEnum
        {
            Red,
            Black
        };

        public readonly TValue Value;

        public readonly uint Key;

        public readonly bool IsLeaf;

        public ColorEnum Color;

        public RedBlackTreeNode<TValue> Parent;

        public RedBlackTreeNode<TValue> Left;

        public RedBlackTreeNode<TValue> Right;

        public static RedBlackTreeNode<TValue> CreateLeaf()
        {
            return new RedBlackTreeNode<TValue>();
        }

        public static RedBlackTreeNode<TValue> CreateNode(uint key, TValue value, ColorEnum color, uint hashedKey)
        {
            return new RedBlackTreeNode<TValue>(key, value, color, hashedKey);
        }

        internal RedBlackTreeNode(uint key, TValue value, ColorEnum color, uint hashedKey)
        {
            Value = value;
            Key = hashedKey;
            Color = color;
            
        }

        internal RedBlackTreeNode()
        {
            IsLeaf = true;
            Color = ColorEnum.Black;
            Key = 0;
        }

        public RedBlackTreeNode<TValue> Grandparent => Parent?.Parent;

        public RedBlackTreeNode<TValue> Sibling =>
            Parent == null ? null : Parent.Left == this ? Parent.Right : Parent.Left;

        public RedBlackTreeNode<TValue> Uncle => Parent?.Sibling;
    }

    public sealed class RedBlackTree<TValue>
    {
        private readonly RedBlackTreeNode<TValue> _leaf = RedBlackTreeNode<TValue>.CreateLeaf();

        public RedBlackTree()
        {
            Root = _leaf;
        }

        [MethodImpl(MethodImplOptions.AggressiveInlining)]
        public TValue Get(uint key)
        {
            try
            {
                RedBlackTreeNode<TValue> node = Root;
                do
                {
                    if (node.Key == key)
                        return node.Value;
                    node = key < node.Key ? node.Left : node.Right;
                } while (true);
            }
            catch (NullReferenceException)
            {
                throw new KeyNotFoundException();
            }
        }

        [MethodImpl(MethodImplOptions.AggressiveInlining)]
        public bool Exists(uint key)
        {
            try
            {
                Get(key);
                return true;
            }
            catch
            {
                return false;
            }

        }

        internal RedBlackTreeNode<TValue> Root { get; private set; }

        public void Add(uint key, TValue value)
        {
            RedBlackTreeNode<TValue> newNode = RedBlackTreeNode<TValue>.CreateNode(key, value, RedBlackTreeNode<TValue>.ColorEnum.Red, key);
            Insert(newNode);
        }

        private void Insert(RedBlackTreeNode<TValue> z)
        {
            var y = _leaf;
            var x = Root;
            while (x != _leaf)
            {
                y = x;
                x = z.Key < x.Key ? x.Left : x.Right;
            }

            z.Parent = y;
            if (y == _leaf)
            {
                Root = z;
            }
            else if (z.Key < y.Key)
            {
                y.Left = z;
            }
            else
            {
                y.Right = z;
            }

            z.Left = _leaf;
            z.Right = _leaf;
            z.Color = RedBlackTreeNode<TValue>.ColorEnum.Red;
            InsertFixup(z);
        }

        private void InsertFixup(RedBlackTreeNode<TValue> z)
        {
            while (z.Parent.Color == RedBlackTreeNode<TValue>.ColorEnum.Red)
            {
                if (z.Parent == z.Parent.Parent.Left)
                {
                    var y = z.Parent.Parent.Right;
                    if (y.Color == RedBlackTreeNode<TValue>.ColorEnum.Red)
                    {
                        z.Parent.Color = RedBlackTreeNode<TValue>.ColorEnum.Black;
                        y.Color = RedBlackTreeNode<TValue>.ColorEnum.Black;
                        z.Parent.Parent.Color = RedBlackTreeNode<TValue>.ColorEnum.Red;
                        z = z.Parent.Parent;
                    }
                    else
                    {
                        if (z == z.Parent.Right)
                        {
                            z = z.Parent;
                            RotateLeft(z);
                        }

                        z.Parent.Color = RedBlackTreeNode<TValue>.ColorEnum.Black;
                        z.Parent.Parent.Color = RedBlackTreeNode<TValue>.ColorEnum.Red;
                        RotateRight(z.Parent.Parent);
                    }
                }
                else
                {
                    var y = z.Parent.Parent.Left;
                    if (y.Color == RedBlackTreeNode<TValue>.ColorEnum.Red)
                    {
                        z.Parent.Color = RedBlackTreeNode<TValue>.ColorEnum.Black;
                        y.Color = RedBlackTreeNode<TValue>.ColorEnum.Black;
                        z.Parent.Parent.Color = RedBlackTreeNode<TValue>.ColorEnum.Red;
                        z = z.Parent.Parent;
                    }
                    else
                    {
                        if (z == z.Parent.Left)
                        {
                            z = z.Parent;
                            RotateRight(z);
                        }

                        z.Parent.Color = RedBlackTreeNode<TValue>.ColorEnum.Black;
                        z.Parent.Parent.Color = RedBlackTreeNode<TValue>.ColorEnum.Red;
                        RotateLeft(z.Parent.Parent);
                    }
                }
            }

            Root.Color = RedBlackTreeNode<TValue>.ColorEnum.Black;
        }

        private void RotateLeft(RedBlackTreeNode<TValue> x)
        {
            var y = x.Right;
            x.Right = y.Left;
            if (y.Left != _leaf)
            {
                y.Left.Parent = x;
            }

            y.Parent = x.Parent;
            if (x.Parent == _leaf)
            {
                Root = y;
            }
            else if (x == x.Parent.Left)
            {
                x.Parent.Left = y;
            }
            else
            {
                x.Parent.Right = y;
            }

            y.Left = x;
            x.Parent = y;
        }

        private void RotateRight(RedBlackTreeNode<TValue> x)
        {
            var y = x.Left;
            x.Left = y.Right;
            if (y.Right != _leaf)
            {
                y.Right.Parent = x;
            }
            y.Parent = x.Parent;
            if (x.Parent == _leaf)
            {
                Root = y;
            }
            else if (x == x.Parent.Left)
            {
                x.Parent.Left = y;
            }
            else
            {
                x.Parent.Right = y;
            }

            y.Right = x;
            x.Parent = y;
        }
    }
}
