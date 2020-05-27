using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Util;
using Android.Views;
using Android.Widget;
using Android.Support.V4.App;
using MidiAranger.Droid.Source.common;
using Java.Lang;
using Android.Animation;
using System.Drawing;

namespace MidiAranger.Droid.Resources.layout
{
    public class PlayFragment : Android.Support.V4.App.Fragment
    {
        IOnActionEventListener onActionEventListener;

        public override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);

            // Create your fragment here
        }


        [Obsolete]
        public override void OnAttach(Activity activity)
        {
            base.OnAttach(activity);
            try
            {
                onActionEventListener = (IOnActionEventListener)activity;
            }
            catch (ClassCastException)
            {
                throw new ClassCastException(activity.ToString() + " must implement IOnActionEventListener");
            }

        }
        public override View OnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            // Use this to return your custom view for this Fragment
            View v = inflater.Inflate(Resource.Layout.FragmentPlay, container, false);

            ((Button)v.FindViewById(Resource.Id.MainA)).Click += (object sender, EventArgs e) => { onActionEventListener.DoAction(Common.MainAAction);};
       //     ((Button)v.FindViewById(Resource.Id.MainB)).Click += (object sender, EventArgs e) => { onActionEventListener.DoAction(Common.MainBAction); };
            ((Button)v.FindViewById(Resource.Id.FillAB)).Click += (object sender, EventArgs e) => { onActionEventListener.DoAction(Common.FillInABAction); };
            ((Button)v.FindViewById(Resource.Id.FillBA)).Click += (object sender, EventArgs e) => { onActionEventListener.DoAction(Common.FillInBAAction); };
            ((Button)v.FindViewById(Resource.Id.EndingB)).Click += (object sender, EventArgs e) => { onActionEventListener.DoAction(Common.EndingBAction); };


            ObjectAnimator colorAnim = ObjectAnimator.OfInt(((Button)v.FindViewById(Resource.Id.MainA)), "textColor",Color.Red.ToArgb(), Color.Transparent.ToArgb());
            colorAnim.SetDuration(1000);
            colorAnim.SetEvaluator(new ArgbEvaluator());
            colorAnim.RepeatCount=ValueAnimator.Infinite;
            colorAnim.RepeatMode = ValueAnimatorRepeatMode.Reverse;
            colorAnim.Start();

            return v;
            
        }

        public void SetTempoAndTact(int tempo, int tact)
        {
            Activity.RunOnUiThread(() => Activity.FindViewById<Source.Views.Indicator.IndicatorView>(Resource.Id.tempoIndicator).SetValue(tempo,tact));
        }


        private void Button_Click(object sender, EventArgs e)
        {
            throw new NotImplementedException();
        }
    }
}