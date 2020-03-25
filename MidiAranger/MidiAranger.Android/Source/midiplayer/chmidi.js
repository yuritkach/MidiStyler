// Copyright 1998, 1999, 2000, 2001, 2002, 2003, 2004 © Erik van der Neut - All rights reserved.
var midi_script="http://www.looknohands.com/cgi-bin/midi_chordhouse/ch_midi.pl";
var debug=0;
var dump=0;
var args=b27();
if(args.bgcolor)
  document.bgColor=args.bgcolor;
function build_instrument_list(){
   document.write("<select name=patch class=cssMidi>");
   
   if(args.instr=="guitar"||args.instr=="all"){
        document.write("<option value=24>Nylon String Guitar</option>" +
            "<option value=25 selected>Steel String Guitar</option>" +
            "<option value=26>Electric Jazz Guitar</option>" +
            "<option value=27>Electric Clean Guitar</option>" +
            "<option value=28>Electric Muted Guitar</option>");
    }
    if (args.instr == "bass" || args.instr == "all") {
        document.write("<option value=32>Acoustic Bass</option>" +
            "<option value=33>Electric Bass (finger)</option>" +
            "<option value=34>Electric Bass (pick)</option>" +
            "<option value=35>Fretless Bass</option>" +
            "<option value=36>Slap Bass 1</option>" +
            "<option value=37>Slap Bass 2</option>");
    }
    if (args.instr == "piano" || args.instr == "all") {
        document.write("<option value=0>Acoustic Grand Piano</option>" +
            "<option value=1>Bright Acoustic Piano</option>" +
            "<option value=2>Electric Grand Piano</option>" +
            "<option value=3>Honky-tonk Piano</option>" +
            "<option value=4>Electric Piano 1</option>" +
            "<option value=5>Electric Piano 2</option>" +
            "<option value=6>Harpsichord</option>" +
            "<option value=7>Clav</option>" +
            "<option value=16>Drawbar Organ</option>" +
            "<option value=17>Percussive Organ</option>" +
            "<option value=18>Rock Organ</option>" +
            "<option value=19>Church Organ</option>" +
            "<option value=20>Reed Organ</option>" +
            "<option value=21>Accordion</option>" +
            "<option value=22>Harmonica</option>" +
            "<option value=23>Tango Accordion</option>" +
            "<option value=80>Synth Lead 1 (square)</option>" +
            "<option value=81>Synth Lead 2 (sawtooth)</option>" +
            "<option value=82>Synth Lead 3 (calliope)</option>" +
            "<option value=83>Synth Lead 4 (chiff)</option>" +
            "<option value=84>Synth Lead 5 (charang)</option>" +
            "<option value=85>Synth Lead 6 (voice)</option>" +
            "<option value=86>Synth Lead 7 (fifths)</option>" +
            "<option value=87>Synth Lead 8 (bass+lead)</option>" +
            "<option value=38>Synth Bass 1</option>" +
            "<option value=39>Synth Bass 2</option>" +
            "<option value=62>Synth Brass 1</option>" +
            "<option value=64>Synth Brass 2</option>" +
            "<option value=50>Synth Strings 1</option>" +
            "<option value=51>Synth Strings 2</option>" +
            "<option value=54>Synth Voice</option>");
    }
    document.write("</select>");
}

function b84(midi_notes, playstyle) {
    var b05 = "debug=" + debug + "&dump=" + dump;
    if (document.midi.mime.value)
        b05 += "&mime=" + document.midi.mime.value;
    if (document.midi.channel.value)
        b05 += "&channel=" + document.midi.channel.value;
    if (document.midi.patch.value)
        b05 += "&patch=" + document.midi.patch.options[document.midi.patch.selectedIndex].value;
    if (midi_notes != "")
        b05 += "&notes=" + midi_notes;
    else
        alert("You haven't yet selected a chord for me to play.\nI'll play an open E Major for you as an example.");

    if (document.midi.bpm.value)
        b05 += "&bpm=" + document.midi.bpm.value;
    if (document.midi.repeats.value)
        b05 += "&repeats=" + document.midi.repeats.value;
    if (playstyle != "")
        b05 += "&playstyle=" + playstyle;
    else
        alert("Warning: `playstyle' parameter is empty; defaulting to guitar chord");

    if (document.midi.volume.value)
        b05 += "&volume=" + document.midi.volume.value;
    return (midi_script + '?' + b05);
}

function setNotes(root, c27) {
    var b06 = "";
    var music_notes = "";
    var b03 = new Array;
    b03["C"] = 48;
    b03["Cis"] = 49;
    b03["Db"] = 49;
    b03["D"] = 50;
    b03["Dis"] = 51;
    b03["Eb"] = 51;
    b03["E"] = 40;
    b03["F"] = 41;
    b03["Fis"] = 42;
    b03["Gb"] = 42;
    b03["G"] = 43;
    b03["Gis"] = 44;
    b03["Ab"] = 44;
    b03["A"] = 45;
    b03["Ais"] = 46;
    b03["Bb"] = 46;
    b03["B"] = 47;

    var a75 = new Array;
    a75["1"] = 0;
    a75["b2"] = 1;
    a75["2"] = 2;
    a75["#2"] = 3;
    a75["b3"] = 3;
    a75["3"] = 4;
    a75["4"] = 5;
    a75["#4"] = 6;
    a75["b5"] = 6;
    a75["5"] = 7;
    a75["#5"] = 8;
    a75["b6"] = 8;
    a75["6"] = 9;
    a75["bb7"] = 9;
    a75["#6"] = 10;
    a75["b7"] = 10;
    a75["7"] = 11;
    a75["8"] = 12;
    a75["b9"] = 13;
    a75["9"] = 14;
    a75["#9"] = 15;
    a75["10"] = 16;
    a75["11"] = 17;
    a75["#11"] = 18;
    a75["12"] = 19;
    a75["b13"] = 20;
    a75["13"] = 21;

    var sep = ",";
    var a85 = -1;
    var a84 = 0;
    var i = 0;
    var b52 = "";
    var b09 = true;
    a84 = c27.indexOf(sep);
    while (b09) {
        if (a84 != -1) {
            b52 = c27.substring(a85 + 1, a84);
        }
        else {
            b52 = c27.substring(a85 + 1, c27.length);
            b09 = false;
        }

        if (b52.charAt(0) == "(") {
            b52 = b52.substring(1, b52.length - 1);
        }
        else {
            var noteCode = b03[root] + a75[b52];
            b06 += noteCode;
            music_notes += convertToNoteName(noteCode);

            if (b09) {
                b06 += ",";
                music_notes += ",";
            }
        }
        if (a84 != -1) {
            a85 = a84;
            a84 = c27.indexOf(sep, a85 + 1);
        }
    }
    return music_notes;
}

function convertToNoteName(noteCode) {
    var noteNames = new Array("C", "Cis", "D", "Dis", "E", "F", "Fis", "G", "Gis", "A", "Ais", "B");
    var music_note = noteNames[noteCode % 12];
    music_note += "_";
    music_note += Math.floor((noteCode / 12) - 1);
    return music_note;
}

function playMidi(midi_notes, playstyle) {
    if (!top.midi_playback)
        return;
    var b98 = navigator.appName;
    var b04 = b84(midi_notes, playstyle);
    top.midi_playback.document.close();
    top.midi_playback.document.write("<html><head>" + "<title>Chord House ::: MIDI Playback</title>" +
        "<meta name=\"copyright\" content=\"&copy; 2003 Erik van der Neut, Boston MA, USA\">" + "</head>\n<body>\n");
    if (b98 == "Microsoft Internet Explorer") {
        top.midi_playback.document.write("<bgsound src=" + b04 + " loop=infinite>");
    }
    else if (b98 == "Netscape") {
        if (navigator.mimeTypes["audio/mid"].enabledPlugin != null) {
            top.midi_playback.document.write("<embed " + " src=" + b04 + " hidden=false loop=true" + " autostart=true width=144 height=60 align=bottom></embed>\n");
        }
        else {
            alert("You do not have a MIDI plugin installed.\nPlease visit www.apple.com/quicktime to install the QuickTime plugin.");
            top.main_window.playing(false);
        }
    }
    else {
        top.midi_playback.document.write("<embed " + " src=" + b04 + " hidden=false loop=true" + " autostart=true width=144 height=60 align=bottom></embed>\n");
    }

    top.midi_playback.document.write("\n</body>\n</html>");
    top.midi_playback.document.close();
    setTimeout("top.main_window.playing(true);", 650);
}

function saveMidi(midi_notes, playstyle, full_name, c25) {
    b04 = b84(midi_notes, playstyle);
    var w_save = window.open("", "ch_midi_save", "height=200,width=250,resizable=0,status=1");
    w_save.focus();
    w_save.document.write("<head><title>chord house ::: save " + full_name + " " + c25 + "</title></head>" + "<body bgcolor=ffcc00>" +
        "<img src=\"../chordhouse_logo_sml_ffcc00.gif\"><p><font face=\"Helvetica,Arial,Geneva\" color=ffffff size=2>" +
        "To save the sound of this<br>" + "<nobr>" + full_name + " " + c25 + "</nobr>,<br>" +
        "<a href=" + b04 + ">right-click on this link</a><br>" + "and choose \"Save target as...\"." + "</font>" + "</body></html>");

    w_save.document.close();
    setTimeout("top.main_window.playing(false);", 2500);
}

function b27() {
    var args = new Object();
    var query = location.search.substring(1);
    var pairs = query.split(",");
    for (var i = 0; i < pairs.length; i++){
        var pos = pairs[i].indexOf("=");
        if (pos == -1)
            continue;
        var c32 = pairs[i].substring(0, pos);
        var value = pairs[i].substring(pos + 1);
        args[c32] = unescape(value);
    }
    return args;
}