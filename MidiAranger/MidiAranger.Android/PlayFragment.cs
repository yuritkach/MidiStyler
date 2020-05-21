﻿using System;
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

            Button button = (Button)v.FindViewById(Resource.Id.MainA);
            button.Click += (object sender, EventArgs e) => { onActionEventListener.DoAction(Common.MainAAction);};

            return v;
            
        }

        private void Button_Click(object sender, EventArgs e)
        {
            throw new NotImplementedException();
        }
    }
}