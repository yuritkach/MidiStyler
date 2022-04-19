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
using MidiAranger.Droid.Source.Views;
using Android.Support.V4.App;
using MidiAranger.Droid.Resources.layout;
using CommonLib;

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
            mplayer = new MIDIPlayer(/*this,*/midiStyle);
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
                //byte[] pressedNotes = new byte[3] {65,69,71};  // test

                if (pressedNotes.Length > 0) {
                    ChordDefinition cd = chordRecognizer.ChordRecognize(pressedNotes);
                    if (cd != null)
                    {
                        //TODO: error on chord 65-69-71 -- ошибка с необязательной нотой аккорда (когда она явялется основной)
                        byte rootnote = pressedNotes[0];
                        if ((cd.RootOffset >= pressedNotes.Length)&& (cd.ChordVariantOffset[cd.RootOffset] < cd.ChordVariantOffset[0]))
                            rootnote =(byte) (pressedNotes[0] - cd.ChordVariantOffset[0]);
                        t = Common.GetNoteName(rootnote) + cd.Chord.ChordName.Substring(1);
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
                case Common.StartAction: mplayer.Start(); break;
                case Common.StopAction: mplayer.Start(); break;
                case Common.MainAAction: mplayer.GotoSection(StyleSections.MainA, false); break;
                case Common.MainBAction: mplayer.GotoSection(StyleSections.MainB, false); break;
                case Common.FillInABAction: mplayer.GotoSection(StyleSections.FillInAB, true); break;
                case Common.FillInBAAction: mplayer.GotoSection(StyleSections.FillInBA, true); break;
                case Common.EndingBAction: mplayer.GotoSection(StyleSections.EndingB, false); break;

                default: new ApplicationException("Action "+s+" not implemented!");break;
            }
        }
    }
}