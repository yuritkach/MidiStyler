using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace MidiAranger.Windows.View
{
    /// <summary>
    /// Interaction logic for IndicatorView.xaml
    /// </summary>
    public partial class IndicatorView : UserControl
    {
        public IndicatorView()
        {
            InitializeComponent();
        }

        public int Value { get; set; }
        public int Places { get; set; }
        private static readonly byte[] segments = new byte[] { 119, 36, 93, 109, 46, 107, 123, 37, 127, 111, 0 };
        private Color ActiveSegmentColor;
        private Color PassiveSegmentColor;
        private int currentTact;
        //       private int tactCount;

        private void Initialize()
        {
            ActiveSegmentColor = Colors.Green;
            PassiveSegmentColor = Colors.DarkGray;
            Places = 3;
            Value = 0; // All segments dark
            currentTact = 0;
            //          tactCount = 4;
        }

        protected override void OnRender(DrawingContext drawingContext)
        {
            base.OnRender(drawingContext);
            string s = Value.ToString();
            while (s.Length < Places) s = ' ' + s;
            for (int i = 0; i < Places; i++)
                DrawPlace(drawingContext, s.Substring(i, 1) == " " ? 10 : int.Parse(s.Substring(i, 1)), i);
        }
        

        private void DrawPlace(DrawingContext drawingContext, int value, int place)
        {
            DrawHorizontalSegment(drawingContext, place * 36 * measureCoefX + 4 * measureCoefX, 3 * measureCoefY, (segments[value] & 1) == 1); //top middle segment
            DrawHorizontalSegment(drawingContext, place * 36 * measureCoefX + 4 * measureCoefX, 27 * measureCoefY, (segments[value] & 8) == 8); //middle middle segment
            DrawHorizontalSegment(drawingContext, place * 36 * measureCoefX + 4 * measureCoefX, 51 * measureCoefY, (segments[value] & 64) == 64); //bottom middle segment

            DrawVerticalSegment(drawingContext, place * 36 * measureCoefX + 3 * measureCoefX, 4 * measureCoefY, (segments[value] & 2) == 2); //top left segment
            DrawVerticalSegment(drawingContext, place * 36 * measureCoefX + 24 * measureCoefX, 4 * measureCoefY, (segments[value] & 4) == 4); //top right segment
            DrawVerticalSegment(drawingContext, place * 36 * measureCoefX + 3 * measureCoefX, 28 * measureCoefY, (segments[value] & 16) == 16); //bottom left segment
            DrawVerticalSegment(drawingContext, place * 36 * measureCoefX + 24 * measureCoefX, 28 * measureCoefY, (segments[value] & 32) == 32); //bottom right segment

            DrawTactSegment(drawingContext, place * 36 * measureCoefX, 56 * measureCoefY, place); //bottom middle segment

        }



        private void DrawHorizontalSegment(DrawingContext drawingContext, float startX, float startY, bool isActiveSegment)
        {
            var myPen = new Pen
            {
                Thickness = 1,
                Brush = new SolidColorBrush(isActiveSegment ? ActiveSegmentColor : PassiveSegmentColor)
            };
            myPen.Freeze();


            var geometry = new StreamGeometry();
            using (StreamGeometryContext ctx = geometry.Open())
            {
                ctx.BeginFigure(new Point(startX, startY), true /* is filled */, true /* is closed */);
                ctx.LineTo(new Point(startX + (3 * measureCoefX), startY - (3 * measureCoefY)), true /* is stroked */, false /* is smooth join */);
                ctx.LineTo(new Point(startX + (16 * measureCoefX), startY - (3 * measureCoefY)), true /* is stroked */, false /* is smooth join */);
                ctx.LineTo(new Point(startX + (19 * measureCoefX), startY), true /* is stroked */, false /* is smooth join */);
                ctx.LineTo(new Point(startX + (16 * measureCoefX), startY + (3 * measureCoefY)), true /* is stroked */, false /* is smooth join */);
                ctx.LineTo(new Point(startX + (3 * measureCoefX), startY + (3 * measureCoefY)), true /* is stroked */, false /* is smooth join */);

            }
            geometry.Freeze();

            drawingContext.DrawGeometry(null, myPen, geometry);
        }

        private void DrawVerticalSegment(DrawingContext drawingContext, float startX, float startY, bool isActiveSegment)
        {
            var myPen = new Pen
            {
                Thickness = 1,
                Brush = new SolidColorBrush(isActiveSegment ? ActiveSegmentColor : PassiveSegmentColor)
            };
            myPen.Freeze();


            var geometry = new StreamGeometry();
            using (StreamGeometryContext ctx = geometry.Open())
            {
                ctx.BeginFigure(new Point(startX, startY), true /* is filled */, true /* is closed */);
                ctx.LineTo(new Point(startX + (3 * measureCoefX), startY + (3 * measureCoefY)), true /* is stroked */, false /* is smooth join */);
                ctx.LineTo(new Point(startX + (3 * measureCoefX), startY + (19 * measureCoefY)), true /* is stroked */, false /* is smooth join */);
                ctx.LineTo(new Point(startX, startY + (22 * measureCoefY)), true /* is stroked */, false /* is smooth join */);
                ctx.LineTo(new Point(startX - (3 * measureCoefX), startY + (19 * measureCoefY)), true /* is stroked */, false /* is smooth join */);
                ctx.LineTo(new Point(startX - (3 * measureCoefX), startY + (3 * measureCoefY)), true /* is stroked */, false /* is smooth join */);

            }
            geometry.Freeze();

            drawingContext.DrawGeometry(null, myPen, geometry);
        }

        private void DrawTactSegment(DrawingContext drawingContext, float x, float y, int place)
        {
            var myPen = new Pen
            {
                Thickness = 1,
                Brush = new SolidColorBrush(IsActiveTact(place) ? ActiveSegmentColor : PassiveSegmentColor)
            };
            myPen.Freeze();


            var geometry = new StreamGeometry();
            using (StreamGeometryContext ctx = geometry.Open())
            {
                ctx.BeginFigure(new Point(x, y), true /* is filled */, true /* is closed */);
                ctx.LineTo(new Point(x + (29 * measureCoefX), y), true /* is stroked */, false /* is smooth join */);
                ctx.LineTo(new Point(x + (29 * measureCoefX), y + (6 * measureCoefY)), true /* is stroked */, false /* is smooth join */);
                ctx.LineTo(new Point(x, y + (6 * measureCoefY)), true /* is stroked */, false /* is smooth join */);
                
            }
            geometry.Freeze();
            drawingContext.DrawGeometry(null, myPen, geometry);

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
            if ((Value != value) || (currentTact != tact))
            {
                Value = value;
                currentTact = tact;
//                Invalidate();
            }


        }

        protected override Size MeasureOverride(Size constraint)
        {
            return base.MeasureOverride(constraint);
            
       //     measureHeight = MeasureHeight(heightMeasureSpec);
       //     measureWidth = MeasureWidth(widthMeasureSpec);
       //     measureCoefX = measureWidth / 6 / 6 / Places;
       //     measureCoefY = measureHeight / 6 / 11;
       //     SetMeasuredDimension(measureWidth, measureHeight);

        }


        private int MeasureHeight(int measureSpec)
        {
//            MeasureSpecMode specMode = MeasureSpec.GetMode(measureSpec);
//            int specSize = MeasureSpec.GetSize(measureSpec);

            int result = 90;
 //           if (specMode == MeasureSpecMode.AtMost)
 //           {
 //               result = specSize;
 //           }
 //           else if (specMode == MeasureSpecMode.Exactly)
 //           {
 //               result = specSize;
 //           }
            return result;
        }

        private int MeasureWidth(int measureSpec)
        {
   //         MeasureSpecMode specMode = MeasureSpec.GetMode(measureSpec);
   //         int specSize = MeasureSpec.GetSize(measureSpec);

            int result = 50;
     //       if (specMode == MeasureSpecMode.AtMost)
    //        {
    //            result = specSize;
    //        }
     //       else if (specMode == MeasureSpecMode.Exactly)
     //       {
     //           result = specSize;
     //       }
            return result;
        }

        private int measureHeight;
        private int measureWidth;
        private float measureCoefX;
        private float measureCoefY;


    }


}
