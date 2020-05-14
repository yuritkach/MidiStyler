using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.Graphics;
using Android.OS;
using Android.Runtime;
using Android.Util;
using Android.Views;
using Android.Widget;

namespace MidiAranger.Droid.Source.Views.Indicator
{
    public class IndicatorView : View
    {
        public int Value { get; set; }
   
        public int Places { get; set; }

        private static readonly byte[] segments = new byte[] {119,36,93,109,46,107,123,37,127,111,0};
            

        public IndicatorView(Context context, IAttributeSet attrs) :
            base(context, attrs)
        {
            Initialize();
        }

        public IndicatorView(Context context, IAttributeSet attrs, int defStyle) :
            base(context, attrs, defStyle)
        {
            Initialize();
        }

        private Color ActiveSegmentColor;
        private Color PassiveSegmentColor;

        private int currentTact;
        private int tactCount;

        private void Initialize()
        {
            ActiveSegmentColor = new Color(100, 100, 100);
            PassiveSegmentColor = new Color(240, 240, 240);
            Places = 3;
            Value = 0; // All segments dark
            currentTact = 0;
            tactCount = 4;
        }

        protected override void OnDraw(Canvas canvas)
        {
            base.OnDraw(canvas);
            string s = Value.ToString();
            while (s.Length < Places) s = ' ' + s;
            for (int i = 0; i < Places; i++)
                DrawPlace(canvas, s.Substring(i, 1) == " " ? 10 : int.Parse(s.Substring(i, 1)), i);
        }

        private void DrawPlace(Canvas canvas, int value, int place)
        {
            DrawHorizontalSegment(canvas, place * 36* measureCoefX + 4 * measureCoefX, 3 * measureCoefY, (segments[value] & 1)==1); //top middle segment
            DrawHorizontalSegment(canvas, place * 36 * measureCoefX + 4 * measureCoefX, 27 * measureCoefY, (segments[value] & 8)==8); //middle middle segment
            DrawHorizontalSegment(canvas, place * 36 * measureCoefX + 4 * measureCoefX, 51 * measureCoefY, (segments[value] & 64)==64); //bottom middle segment

            DrawVerticalSegment(canvas, place * 36 * measureCoefX + 3 * measureCoefX, 4 * measureCoefY, (segments[value] & 2)==2); //top left segment
            DrawVerticalSegment(canvas, place * 36 * measureCoefX + 24 * measureCoefX, 4 * measureCoefY, (segments[value] & 4)==4); //top right segment
            DrawVerticalSegment(canvas, place * 36 * measureCoefX + 3 * measureCoefX, 28 * measureCoefY, (segments[value] & 16)==16); //bottom left segment
            DrawVerticalSegment(canvas, place * 36 * measureCoefX + 24 * measureCoefX, 28 * measureCoefY, (segments[value] & 32)==32); //bottom right segment

            DrawTactSegment(canvas, place * 36 * measureCoefX, 56 * measureCoefY, place); //bottom middle segment




        }



        private void DrawHorizontalSegment(Canvas canvas, float startX, float startY, bool isActiveSegment)
        {
            Paint paint = new Paint();
            paint.Color = isActiveSegment ? ActiveSegmentColor : PassiveSegmentColor;
            paint.SetStyle(Paint.Style.Fill);
            Path path = new Path();
            path.Reset(); 
            path.MoveTo(startX, startY); 
            path.LineTo(startX + (3 * measureCoefX), startY - (3 * measureCoefY));
            path.LineTo(startX + (16 * measureCoefX), startY - (3 * measureCoefY));
            path.LineTo(startX +(19 * measureCoefX),startY);
            path.LineTo(startX + (16 * measureCoefX), startY + (3 * measureCoefY));
            path.LineTo(startX + (3 * measureCoefX), startY + (3 * measureCoefY));
            canvas.DrawPath(path, paint);
        }

        private void DrawVerticalSegment(Canvas canvas, float startX, float startY, bool isActiveSegment)
        {
            Paint paint = new Paint();
            paint.Color = isActiveSegment ? ActiveSegmentColor : PassiveSegmentColor;
            paint.SetStyle(Paint.Style.Fill);
            Path path = new Path();
            path.Reset(); 
            path.MoveTo(startX, startY);
            path.LineTo(startX + (3 * measureCoefX), startY + (3 * measureCoefY));
            path.LineTo(startX + (3 * measureCoefX), startY + (19 * measureCoefY));
            path.LineTo(startX , startY + (22 * measureCoefY));
            path.LineTo(startX - (3 * measureCoefX), startY + (19 * measureCoefY));
            path.LineTo(startX - (3 * measureCoefX), startY + (3 * measureCoefY));
            canvas.DrawPath(path, paint);
        }

        private void DrawTactSegment(Canvas canvas, float x, float y, int place)
        {
            Paint paint = new Paint();
            paint.Color = IsActiveTact(place) ? ActiveSegmentColor : PassiveSegmentColor;
            paint.SetStyle(Paint.Style.Fill);
            Path path = new Path();
            path.Reset();
            path.MoveTo(x, y);
            path.LineTo(x + (29 * measureCoefX), y);
            path.LineTo(x + (29 * measureCoefX), y + (6 * measureCoefY));
            path.LineTo(x , y + (6 * measureCoefY));
            canvas.DrawPath(path, paint);

        }

        private bool IsActiveTact(int place)
        {
            if (currentTact == 0) return true;
            if (currentTact == place + 1) return true;
            if (currentTact > 3 && place == 2) return true;
            return false;
        }


        public void SetValue(int value, int tact)
        {
            if ((Value != value)|| (currentTact!=tact))
            {
                Value = value;
                currentTact = tact;
                Invalidate();
            }
            

        }


        protected override void OnMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            base.OnMeasure(widthMeasureSpec, heightMeasureSpec);
            measureHeight = MeasureHeight(heightMeasureSpec);
            measureWidth = MeasureWidth(widthMeasureSpec);
            measureCoefX = measureWidth / 6 / 6 / Places;
            measureCoefY = measureHeight / 6 / 11;
            SetMeasuredDimension(measureWidth,measureHeight);
        }


        private int MeasureHeight(int measureSpec)
        {
            MeasureSpecMode specMode = MeasureSpec.GetMode(measureSpec);
            int specSize = MeasureSpec.GetSize(measureSpec);

            int result = 90;
            if (specMode == MeasureSpecMode.AtMost)
            {
                result = specSize;
            }
            else if (specMode == MeasureSpecMode.Exactly)
            {
                result = specSize;
            }
            return result;
        }

        private int MeasureWidth(int measureSpec)
        {
            MeasureSpecMode specMode = MeasureSpec.GetMode(measureSpec);
            int specSize = MeasureSpec.GetSize(measureSpec);

            int result = 50;
            if (specMode == MeasureSpecMode.AtMost)
            {
                result = specSize;
            }
            else if (specMode == MeasureSpecMode.Exactly)
            {
                result = specSize;
            }
            return result;
        }

        private int measureHeight;
        private int measureWidth;
        private float measureCoefX;
        private float measureCoefY;


    }
}