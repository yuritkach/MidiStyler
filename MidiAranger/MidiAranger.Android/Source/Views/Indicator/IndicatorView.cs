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
        private int value;

        private static readonly byte[] segments = new byte[] {119,36,125,109,46,107,123,37,127,111};
            

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

        private void Initialize()
        {
            ActiveSegmentColor = new Color(100, 255, 100);
            PassiveSegmentColor = new Color(200, 200, 200);
        }

        protected override void OnDraw(Canvas canvas)
        {
            base.OnDraw(canvas);
            DrawValue(canvas, 1);

            

        }

        private void DrawValue(Canvas canvas, int value)
        {
            DrawHorizontalSegment(canvas, 4 * measureCoefX, 3 * measureCoefY, (segments[value] & 1)==1); //top middle segment
            DrawHorizontalSegment(canvas, 4 * measureCoefX, 27 * measureCoefY, (segments[value] & 1)==8); //middle middle segment
            DrawHorizontalSegment(canvas, 4 * measureCoefX, 51 * measureCoefY, (segments[value] & 1)==64); //bottom middle segment

            DrawVerticalSegment(canvas, 3 * measureCoefX, 4 * measureCoefY, (segments[value] & 2)==2); //top left segment
            DrawVerticalSegment(canvas, 24 * measureCoefX, 4 * measureCoefY, (segments[value] & 4)==4); //top right segment
            DrawVerticalSegment(canvas, 3 * measureCoefX, 28 * measureCoefY, (segments[value] & 16)==16); //bottom left segment
            DrawVerticalSegment(canvas, 24 * measureCoefX, 28 * measureCoefY, (segments[value] & 32)==32); //bottom right segment

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




        protected override void OnMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            base.OnMeasure(widthMeasureSpec, heightMeasureSpec);
            measureHeight = MeasureHeight(heightMeasureSpec);
            measureWidth = MeasureWidth(widthMeasureSpec);
            measureCoefX = measureWidth / 6 / 6;
            measureCoefY = measureWidth / 6 / 10;
            SetMeasuredDimension(measureHeight,measureWidth);
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