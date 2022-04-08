using System;

using Android.App;
using Android.Content.PM;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Android.OS;
using Android.Util;
using midi;
using Xamarin.Forms;
using midi.events;
using System.Threading;
using MidiAranger.Droid.Source.midiplayer;
using static MidiAranger.Droid.Source.midiplayer.ChordRecognizer;
using MidiAranger.Droid.Source.styler;
using static MidiAranger.Droid.Source.common.Common;
using MidiAranger.Droid.Source.common;
using MidiAranger.Droid.Source.Views;
using Android.Support.V4.App;
using MidiAranger.Droid.Resources.layout;

namespace MidiAranger.Droid
{
    [Activity(Label = "MidiAranger", Icon = "@mipmap/icon", Theme = "@style/MainTheme", MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation)]
    public partial class MainActivity : Android.Support.V4.App.FragmentActivity, IOnActionEventListener
    {
        private PlayFragment playFragment;
        private ToolBarFragment toolbarFragment;
        
        MIDIPlayer mplayer;
        ChordRecognizer chordRecognizer;
        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);
            global::Xamarin.Forms.Forms.Init(this, savedInstanceState);
            SetContentView(Resource.Layout.ActivityMain);
            Android.Support.V4.App.FragmentManager fm = SupportFragmentManager;
            playFragment = (PlayFragment)fm.FindFragmentById(Resource.Layout.FragmentPlay);
            if (playFragment == null)
            {
                playFragment = new PlayFragment();
                Android.Support.V4.App.FragmentTransaction ft = fm.BeginTransaction();
                ft.Add(Resource.Id.main_container, playFragment);
                ft.Commit();
            }
            toolbarFragment = (ToolBarFragment)fm.FindFragmentById(Resource.Layout.FragmentToolBar);
            if (toolbarFragment == null)
            {
                toolbarFragment = new ToolBarFragment();
                Android.Support.V4.App.FragmentTransaction ft = fm.BeginTransaction();
                ft.Add(Resource.Id.toolbar_container, toolbarFragment);
                ft.Commit();
            }

            chordRecognizer = new ChordRecognizer();

            MIDIStyle midiStyle = new MIDIStyle();
            midiStyle.LoadStyle("ddd");
            mplayer = new MIDIPlayer(this,midiStyle);
            mplayer.OnTactEvent += (object sender, OnTactEventArgs e)=> playFragment.SetTempoAndTact(e.CurrentTempo, e.CurrentTact);
            mplayer.OnChordChangeEvent += ()=> OnChordChanged();
            mplayer.OnSectionChangeEvent += () => OnSectionChanged();

            mplayer.Tracks = midiStyle.MidiSection.Tracks;
            mplayer.Start();

        }

        

        protected void OnChordChanged()
        {
            RunOnUiThread(() => {
                if (mplayer.currentPressedNotes == null) return;
                string t = "";
                byte[] pressedNotes = mplayer.currentPressedNotes.ToArray();
                if (pressedNotes.Length > 0) {
                    ChordDefinition cd = chordRecognizer.ChordRecognize(pressedNotes);
                    if (cd != null)
                    {
                        //TODO: error on chord 65-69-71
                        t = Common.GetNoteName(pressedNotes[cd.RootOffset]) + cd.Chord.ChordName.Substring(1) + " -- " + pressedNotes[cd.RootOffset].ToString();
                    }
                }
                FindViewById<TextView>(Resource.Id.currentChord).Text = t;
            });
            
        }

        protected void OnSectionChanged()
        {
            RunOnUiThread(() => {
                playFragment.SetButtonMode(mplayer.CurrentMarker.Section, mplayer.NextMarker.Section);

            });

        }

        public void DoAction(string s)
        {
            switch (s)
            {
                case StartAction: mplayer.Start(); break;
                case StopAction: mplayer.Start(); break;
                case MainAAction: mplayer.GotoSection(StyleSections.MainA, false); break;
                case MainBAction: mplayer.GotoSection(StyleSections.MainB, false); break;
                case FillInABAction: mplayer.GotoSection(StyleSections.FillInAB, true); break;
                case FillInBAAction: mplayer.GotoSection(StyleSections.FillInBA, true); break;
                case EndingBAction: mplayer.GotoSection(StyleSections.EndingB, false); break;

                default: new ApplicationException("Action "+s+" not implemented!");break;
            }
        }
    }
}