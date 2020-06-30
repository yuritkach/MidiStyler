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
using MidiAranger.Droid.Source.Views;
using static MidiAranger.Droid.Source.Views.StylerButton;

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
        
        private View fragmentView;
        public override View OnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            // Use this to return your custom view for this Fragment
            fragmentView = inflater.Inflate(Resource.Layout.FragmentPlay, container, false);

            ((StylerButton)fragmentView.FindViewById(Resource.Id.MainA)).Click += (object sender, EventArgs e) => { onActionEventListener.DoAction(Common.MainAAction);};
       //     ((Button)v.FindViewById(Resource.Id.MainB)).Click += (object sender, EventArgs e) => { onActionEventListener.DoAction(Common.MainBAction); };
            ((StylerButton)fragmentView.FindViewById(Resource.Id.FillA)).Click += (object sender, EventArgs e) => { onActionEventListener.DoAction(Common.FillInABAction); };
            ((StylerButton)fragmentView.FindViewById(Resource.Id.FillB)).Click += (object sender, EventArgs e) => { onActionEventListener.DoAction(Common.FillInBAAction); };
            ((StylerButton)fragmentView.FindViewById(Resource.Id.EndingB)).Click += (object sender, EventArgs e) => { onActionEventListener.DoAction(Common.EndingBAction); };

            SetInitialButtonState();
            return fragmentView;
        }



        protected void SetInitialButtonState()
        {
            foreach (StyleSections section in System.Enum.GetValues(typeof(StyleSections)))
                GetButtonBySection(section)?.SetMode(StylerButtonMode.sbDisabled);
        }

        protected StylerButton GetButtonBySection(StyleSections currentSection)
        {
            int resId;
            switch (currentSection)
            {
                case StyleSections.IntroA: resId = Resource.Id.IntroA;break;
                case StyleSections.IntroB: resId = Resource.Id.IntroB; break;
                case StyleSections.IntroC: resId = Resource.Id.IntroC; break;
                case StyleSections.IntroD: resId = Resource.Id.IntroD; break;// false section


                case StyleSections.MainA: resId = Resource.Id.MainA; break;
                case StyleSections.MainB: resId = Resource.Id.MainB; break;
                case StyleSections.MainC: resId = Resource.Id.MainC; break;
                case StyleSections.MainD: resId = Resource.Id.MainD; break;

                case StyleSections.FillInAA: resId = Resource.Id.FillA; break;

                case StyleSections.FillInAB: resId = Resource.Id.FillB; break;
                case StyleSections.FillInBA: resId = Resource.Id.FillA; break;
                case StyleSections.FillInBB: resId = Resource.Id.FillB; break;
                case StyleSections.FillInCC: resId = Resource.Id.FillC; break;
                case StyleSections.FillInDD: resId = Resource.Id.FillD; break;

                case StyleSections.EndingA: resId = Resource.Id.EndingA; break;
                case StyleSections.EndingB: resId = Resource.Id.EndingB; break;
                case StyleSections.EndingC: resId = Resource.Id.EndingC; break;
                case StyleSections.EndingD: resId = Resource.Id.EndingD; break; // false section

                default: return null;
            }
            return (StylerButton)fragmentView.FindViewById(resId);
        }

        public void SetButtonMode(StyleSections section, StylerButtonMode mode)
        {
            GetButtonBySection(section)?.SetMode(mode);
        }


        private int oldTempo = 0;
        private int oldTact = 0;
        public void SetTempoAndTact(int tempo, int tact)
        {
            if (tempo!=oldTempo || tact != oldTact)
            {
                oldTact = tact;
                oldTempo = tempo;
                Activity.RunOnUiThread(() => Activity.FindViewById<Source.Views.IndicatorView>(Resource.Id.tempoIndicator).SetValue(tempo, tact));
            }
            
        }

    }
}