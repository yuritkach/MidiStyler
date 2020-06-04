using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Android.Animation;
using Android.App;
using Android.Content;
using Android.Graphics;
using Android.OS;
using Android.Runtime;
using Android.Util;
using Android.Views;
using Android.Views.Animations;
using Android.Widget;
using static Android.Animation.ValueAnimator;

namespace MidiAranger.Droid.Source.Views
{

    public class StylerButton : ImageButton
    {

        protected static List<StylerButton> flashButtons = new List<StylerButton>();
        protected static System.Threading.Timer timer = new System.Threading.Timer((a) =>
        {
            foreach (var o in flashButtons) o.OnFlashTimerTick();
        }, null, 0, 10000);


        protected void OnFlashTimerTick()
        {
            //
        }


        public enum StylerButtonMode {sbDisabled,sbEnabled,SbActive,sbFlash}
        public StylerButtonMode Mode { get; protected set; }
        public void SetMode(StylerButtonMode value)
        {
                Mode = value;
                switch (value)
                {
                    case StylerButtonMode.SbActive:
                        Enabled = true;
                        SetFlashing(false);
                        break;
                    case StylerButtonMode.sbDisabled:
                        SetFlashing(false);
                        Enabled = false;
                        break;
                    case StylerButtonMode.sbEnabled:
                        SetFlashing(false);
                        Enabled = true;
                        break;
                    case StylerButtonMode.sbFlash:
                        SetFlashing(true);
                        Enabled = true;
                        ; break;
                    default:break;
                }
        }
        public StylerButton(Context context, IAttributeSet attrs) :
            base(context, attrs)
        {
            Initialize();
        }

        public StylerButton(Context context, IAttributeSet attrs, int defStyle) :
            base(context, attrs, defStyle)
        {
            Initialize();
        }

        private void Initialize()
        {
            
        }

        protected void SetFlashing(bool flashing)
        {
            if (flashing)
                flashButtons.Add(this);
            else
                flashButtons.Remove(this);
        } 

        protected override void OnMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            measureHeight = MeasureHeight(heightMeasureSpec);
            measureWidth = MeasureWidth(widthMeasureSpec);
            SetMeasuredDimension(measureWidth, measureHeight);
        }

        private bool isLight;
        protected override void OnDraw(Canvas canvas)
        {
            base.OnDraw(canvas);

            Paint paint = new Paint();

            switch (Mode)
            {
                case StylerButtonMode.sbDisabled:paint.Color = new Color(200,200,200); break;
                case StylerButtonMode.sbEnabled: paint.Color = new Color(0, 0, 0); break;
                case StylerButtonMode.SbActive:
                case StylerButtonMode.sbFlash:
                    if (isLight)
                        paint.Color = new Color(0, 200, 0);
                    else paint.Color = new Color(0, 100, 0);
                    break;
                default: throw new ApplicationException("Mode undefined!!!");
            }

            paint.SetStyle(Paint.Style.Fill);
            Path path = new Path();
            path.Reset();
            path.MoveTo(measureWidth/2, measureHeight/2);
            path.AddCircle(measureWidth / 2, measureHeight / 2, measureHeight / 6,Path.Direction.Ccw);
            canvas.DrawPath(path, paint);
        }

        private int MeasureHeight(int measureSpec)
        {
            MeasureSpecMode specMode = MeasureSpec.GetMode(measureSpec);
            int specSize = MeasureSpec.GetSize(measureSpec);

            int result = this.SuggestedMinimumHeight;
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

            int result = this.SuggestedMinimumWidth;
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

        public void OnAnimationUpdate(ValueAnimator animation)
        {
            isLight = !isLight;
            Invalidate(); 
        }

        private int measureHeight;
        private int measureWidth;
    }
}