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

        private void Initialize()
        {
        }

        protected override void OnDraw(Canvas canvas)
        {
            base.OnDraw(canvas);
            DrawValue(canvas, value);

            

        }

        private void DrawValue(Canvas canvas, int value)
        {
            DrawHorizontalSegment(4 * measureCoefX, 3 * measureCoefY, segments[value] & 1); //top middle segment
            DrawHorizontalSegment(4 * measureCoefX, 27 * measureCoefY, segments[value] & 1); //middle middle segment
            DrawHorizontalSegment(4 * measureCoefX, 51 * measureCoefY, segments[value] & 1); //bottom middle segment

            DrawVerticalSegment(4 * measureCoefX, 3 * measureCoefY, segments[value] & 1); //top middle segment
            DrawVerticalSegment(4 * measureCoefX, 27 * measureCoefY, segments[value] & 1); //top middle segment
            DrawVerticalSegment(4 * measureCoefX, 51 * measureCoefY, segments[value] & 1); //top middle segment
            DrawVerticalSegment(4 * measureCoefX, 51 * measureCoefY, segments[value] & 1); //top middle segment



            canvas.DrawRect(1, 1, measureHeight, measureWidth, new Paint());

        }


        protected override void OnMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            base.OnMeasure(widthMeasureSpec, heightMeasureSpec);
            measureHeight = MeasureHeight(heightMeasureSpec);
            measureWidth = MeasureWidth(widthMeasureSpec);
            measureCoefX = measureWidth / 6 / 5;
            measureCoefY = measureWidth / 6 / 9;
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