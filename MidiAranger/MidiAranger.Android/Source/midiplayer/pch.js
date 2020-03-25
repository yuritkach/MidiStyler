// Copyright 1998, 1999, 2000, 2001, 2002, 2003, 2004 © Erik van der Neut - All rights reserved.
if (document) {
    var noteOffset = new Array;
    noteOffset["C"] = 1;
    noteOffset["C#"] = 2;
    noteOffset["Db"] = 2;
    noteOffset["D"] = 3;
    noteOffset["D#"] = 4;
    noteOffset["Eb"] = 4;
    noteOffset["E"] = 5;
    noteOffset["F"] = 6;
    noteOffset["F#"] = 7;
    noteOffset["Gb"] = 7;
    noteOffset["G"] = 8;
    noteOffset["G#"] = 9;
    noteOffset["Ab"] = 9;
    noteOffset["A"] = 10;
    noteOffset["A#"] = 11;
    noteOffset["Bb"] = 11;
    noteOffset["B"] = 12;

    var c38 = new Array("C", "C#", "Db", "D", "E", "F", "F#", "Gb", "G", "G#", "Ab", "A", "A#", "Bb", "B");
    c38["C"] = new Array("C", "D", "E", "F", "G", "A", "B");
    c38["C#"] = new Array("C#", "D#", "E#", "F#", "G#", "A#", "B#");
    c38["Db"] = new Array("Db", "Eb", "F", "Gb", "Ab", "Bb", "C");
    c38["D"] = new Array("D", "E", "F#", "G", "A", "B", "C#");
    c38["D#"] = new Array("D#", "E#", "F##", "G#", "A#", "B#", "C##");
    c38["Eb"] = new Array("Eb", "F", "G", "Ab", "Bb", "C", "D");
    c38["E"] = new Array("E", "F#", "G#", "A", "B", "C#", "D#");
    c38["F"] = new Array("F", "G", "A", "Bb", "C", "D", "E");
    c38["F#"] = new Array("F#", "G#", "A#", "B", "C#", "D#", "E#");
    c38["Gb"] = new Array("Gb", "Ab", "Bb", "Cb", "Db", "Eb", "F");
    c38["G"] = new Array("G", "A", "B", "C", "D", "E", "F#");
    c38["G#"] = new Array("G#", "A#", "B#", "C#", "D#", "E#", "F##");
    c38["Ab"] = new Array("Ab", "Bb", "C", "Db", "Eb", "F", "G");
    c38["A"] = new Array("A", "B", "C#", "D", "E", "F#", "G#");
    c38["A#"] = new Array("A#", "B#", "C##", "D#", "E#", "F##", "G##");
    c38["Bb"] = new Array("Bb", "C", "D", "Eb", "F", "G", "A");
    c38["B"] = new Array("B", "C#", "D#", "E", "F#", "G#", "A#");

    var halfTones = new Array;
    halfTones["1"] = 0;
    halfTones["b2"] = 1;
    halfTones["2"] = 2;
    halfTones["#2"] = 3;
    halfTones["b3"] = 3;
    halfTones["3"] = 4;
    halfTones["4"] = 5;
    halfTones["#4"] = 6;
    halfTones["b5"] = 6;
    halfTones["5"] = 7;
    halfTones["#5"] = 8;
    halfTones["b6"] = 8;
    halfTones["6"] = 9;
    halfTones["bb7"] = 9;
    halfTones["#6"] = 10;
    halfTones["b7"] = 10;
    halfTones["7"] = 11;
    halfTones["8"] = 12;
    halfTones["b9"] = 13;
    halfTones["9"] = 14;
    halfTones["#9"] = 15;
    halfTones["10"] = 16;
    halfTones["11"] = 17;
    halfTones["#11"] = 18;
    halfTones["12"] = 19;
    halfTones["b13"] = 20;
    halfTones["13"] = 21;

    var mde = "Chord";
    var Chords = new Array();
    var a81 = 0;
    var b63 = new Array();
    var a73 = new Array();
    var a80 = 0;
    var a74 = new Array();
    var c08 = new Array();
    var c09 = new Array();
    var c10 = 0;
    var c11 = 1;
    var displayNames = "nn";
    var c13 = "STRICT";
    var root_disambig_sharp_or_flat = "#";
    var c41 = "";
    w = new Image();  w.src = "kw.gif";
    wp = new Image(); wp.src = "kwp.gif";
    wo = new Image(); wo.src = "kwo.gif";
    b = new Image();  b.src = "kb.gif";
    bp = new Image(); bp.src = "kbp.gif";
    bo = new Image(); bo.src = "kbo.gif";
    i1 = new Image(); i1.src = w.src;
    i1p = new Image(); i1p.src = wp.src;
    i1o = new Image(); i1o.src = wo.src;
    i2 = new Image(); i2.src = b.src;
    i2p = new Image(); i2p.src = bp.src;
    i2o = new Image(); i2o.src = bo.src;
    i3 = new Image(); i3.src = w.src;
    i3p = new Image(); i3p.src = wp.src;
    i3o = new Image(); i3o.src = wo.src;
    i4 = new Image(); i4.src = b.src;
    i4p = new Image(); i4p.src = bp.src;
    i4o = new Image(); i4o.src = bo.src;
    i5 = new Image(); i5.src = w.src;
    i5p = new Image(); i5p.src = wp.src;
    i5o = new Image(); i5o.src = wo.src;
    i6 = new Image(); i6.src = w.src;
    i6p = new Image(); i6p.src = wp.src;
    i6o = new Image(); i6o.src = wo.src;
    i7 = new Image(); i7.src = b.src;
    i7p = new Image(); i7p.src = bp.src;
    i7o = new Image(); i7o.src = bo.src;
    i8 = new Image(); i8.src = w.src;
    i8p = new Image(); i8p.src = wp.src;
    i8o = new Image(); i8o.src = wo.src;
    i9 = new Image(); i9.src = b.src;
    i9p = new Image(); i9p.src = bp.src;
    i9o = new Image(); i9o.src = bo.src;
    i10 = new Image(); i10.src = w.src;
    i10p = new Image(); i10p.src = wp.src;
    i10o = new Image(); i10o.src = wo.src;
    i11 = new Image();  i11.src = b.src;
    i11p = new Image(); i11p.src = bp.src;
    i11o = new Image(); i11o.src = bo.src;
    i12 = new Image(); i12.src = w.src;
    i12p = new Image(); i12p.src = wp.src;
    i12o = new Image(); i12o.src = wo.src;
    i13 = new Image(); i13.src = w.src;
    i13p = new Image(); i13p.src = wp.src;
    i13o = new Image(); i13o.src = wo.src;
    i14 = new Image(); i14.src = b.src;
    i14p = new Image();i14p.src = bp.src;
    i14o = new Image(); i14o.src = bo.src;
    i15 = new Image(); i15.src = w.src;
    i15p = new Image(); i15p.src = wp.src;
    i15o = new Image(); i15o.src = wo.src;
    i16 = new Image(); i16.src = b.src;
    i16p = new Image(); i16p.src = bp.src;
    i16o = new Image(); i16o.src = bo.src;
    i17 = new Image(); i17.src = w.src;
    i17p = new Image(); i17p.src = wp.src;
    i17o = new Image(); i17o.src = wo.src;
    i18 = new Image(); i18.src = w.src;
    i18p = new Image(); i18p.src = wp.src;
    i18o = new Image(); i18o.src = wo.src;
    i19 = new Image(); i19.src = b.src;
    i19p = new Image(); i19p.src = bp.src;
    i19o = new Image(); i19o.src = bo.src;
    i20 = new Image(); i20.src = w.src;
    i20p = new Image(); i20p.src = wp.src;
    i20o = new Image(); i20o.src = wo.src;
    i21 = new Image(); i21.src = b.src;
    i21p = new Image(); i21p.src = bp.src;
    i21o = new Image(); i21o.src = bo.src;
    i22 = new Image(); i22.src = w.src;
    i22p = new Image(); i22p.src = wp.src;
    i22o = new Image(); i22o.src = wo.src;
    i23 = new Image(); i23.src = b.src;
    i23p = new Image(); i23p.src = bp.src;
    i23o = new Image(); i23o.src = bo.src;
    i24 = new Image(); i24.src = w.src;
    i24p = new Image(); i24p.src = wp.src;
    i24o = new Image(); i24o.src = wo.src;
    i25 = new Image(); i25.src = w.src;
    i25p = new Image(); i25p.src = wp.src;
    i25o = new Image(); i25o.src = wo.src;
    i26 = new Image(); i26.src = b.src;
    i26p = new Image(); i26p.src = bp.src;
    i26o = new Image(); i26o.src = bo.src;
    i27 = new Image(); i27.src = w.src;
    i27p = new Image(); i27p.src = wp.src;
    i27o = new Image(); i27o.src = wo.src;
    i28 = new Image(); i28.src = b.src;
    i28p = new Image(); i28p.src = bp.src;
    i28o = new Image(); i28o.src = bo.src;
    i29 = new Image(); i29.src = w.src;
    i29p = new Image(); i29p.src = wp.src;
    i29o = new Image(); i29o.src = wo.src;
    i30 = new Image(); i30.src = w.src;
    i30p = new Image(); i30p.src = wp.src;
    i30o = new Image(); i30o.src = wo.src;
    i31 = new Image(); i31.src = b.src;
    i31p = new Image(); i31p.src = bp.src;
    i31o = new Image(); i31o.src = bo.src;
    i32 = new Image(); i32.src = w.src;
    i32p = new Image(); i32p.src = wp.src;
    i32o = new Image(); i32o.src = wo.src;
    i33 = new Image(); i33.src = b.src;
    i33p = new Image(); i33p.src = bp.src;
    i33o = new Image(); i33o.src = bo.src;
    i34 = new Image(); i34.src = w.src;
    i34p = new Image();i34p.src = wp.src;
    i34o = new Image(); i34o.src = wo.src;
    i35 = new Image();i35.src = b.src;
    i35p = new Image(); i35p.src = bp.src;
    i35o = new Image(); i35o.src = bo.src;
    i36 = new Image();i36.src = w.src;
    i36p = new Image(); i36p.src = wp.src;
    i36o = new Image(); i36o.src = wo.src;

    a79();
    a76();

    var NS = (window.Event) ? 1 : 0;
    if (NS)
        document.captureEvents(Event.KEYPRESS);

    document.onkeypress = c46;
}

function c46(e) {
    var code = (NS) ? e.which : event.keyCode;
    var key = String.fromCharCode(code);
    if (key == 'd' || key == 'D')
        toggleInNn();
    if (key == 's' || key == 'S')
        toggleSpelling();
    if (key == 'r' || key == 'R')
        toggleSharpFlat();
}

function setRadioButtons() {
    if (displayNames == "nn") {
        document.formChordsAndScales.displayNames[0].checked = 1;
        document.formChordsAndScales.displayNames[1].checked = 0;
    }
    else {
        document.formChordsAndScales.displayNames[0].checked = 0;
        document.formChordsAndScales.displayNames[1].checked = 1;
    }

    if (c13 == "STRICT") {
        document.formChordsAndScales.enharmonicSpelling[0].checked=1;
        document.formChordsAndScales.enharmonicSpelling[1].checked=0;
    }
    else {
        document.formChordsAndScales.enharmonicSpelling[0].checked=0;
        document.formChordsAndScales.enharmonicSpelling[1].checked=1;
    }
    
    if(root_disambig_sharp_or_flat=="#"){
        document.formChordsAndScales.rootSharpOrFlat[0].checked=1;
        document.formChordsAndScales.rootSharpOrFlat[1].checked=0;
    }
    else {
        document.formChordsAndScales.rootSharpOrFlat[0].checked=0;
        document.formChordsAndScales.rootSharpOrFlat[1].checked=1;
    }
}

function toggleInNn(){
    if(displayNames=="in")
        switchInNn("nn");
    else switchInNn("in");
    
    setRadioButtons();
}

function switchInNn(a33){
    if(a33==displayNames)
        return ;
    displayNames=a33;
    showFingerSetting(mde);
}

function toggleSpelling(){
    if(c13=="STRICT")
        switchSpelling("SIMPLE");
    else 
        switchSpelling("STRICT");
    
    setRadioButtons();
}

function switchSpelling(a33){
    if(a33==c13)
        return ;
    c13=a33;
    showFingerSetting(mde);
}

function toggleSharpFlat(){
    if(root_disambig_sharp_or_flat=="#")
        switchSharpFlat("b");
    else 
        switchSharpFlat("#");
    
    setRadioButtons();
}

function switchSharpFlat(a33){
    if(a33==root_disambig_sharp_or_flat)
        return ;
    root_disambig_sharp_or_flat=a33;
    showFingerSetting(mde);
}

function imgOn(b21){
    document[b21].src=eval(b21+"on.src");
}

function imgOff(b21){
    document[b21].src=eval(b21+"off.src");
}

function b20(name,a58){
    document[name].src=a58;
}

function FirstChord(){
    document.formChordsAndScales.chordRoot[0].selected=true;
    document.formChordsAndScales.chordName[0].selected=true;
    showFingerSetting("Chords");
}

function chord(a47,strchordName,a53,a50){
    Chords[strchordName]=a47+"|"+a53+"|"+a50;
    a81++;
    b63[a81]=strchordName;
}

function scale(a20,a21,a53,a22){
    a73[a21]=a20+"|"+a53+"|"+a22;
    a80++;
    a74[a80]=a21;
}

function addChordOptions(){
    for(i=1;i<=a81;i++){
        document.write("<option value=\""+b63[i].substring(1,b63[i].length)+"\">"+b63[i].substring(1,b63[i].length));
    }
}

function addScaleOptions(){
    for(i=1;i<=a80;i++){
        document.write("<option value=\""+a74[i].substring(0,a74[i].length)+"\">"+a74[i].substring(0,a74[i].length));
    }
}

function a79(){
    chord("Major", "R Major", "<R>, <R>Maj, <R>M", "1,3,5");
    chord("", "R5", "<R> power chord", "1,5");
    chord("Major", "R-5", "<R>(b5), <R> flattened 5th", "1,3,b5");
    chord("Major", "R6", "<R>Maj6, <R>M6", "1,3,5,6");
    chord("Major", "R6/9", "<R>6add9, <R>6(add9), <R>Maj6(add9), <R>M6(add9)", "1,3,(5),6,9");
    chord("Major", "R7", "<R> Dominant 7", "1,3,(5),b7");
    chord("Major", "Radd9", "<R> added 9", "1,3,5,9");
    chord("Major", "Rmaj7", "<R>Maj7, <R>M7", "1,3,5,7");
    chord("Major", "Rmaj7+5", "<R>Maj7#5, <R>M7+5", "1,3,#5,7");
    chord("Major", "Rmaj9", "<R>Maj7(add9), <R>M7(add9)", "1,3,(5),7,9");
    chord("Major", "Rmaj11", "<R>Maj7(add11), <R>M7(add11)", "1,(3),5,7,(9),11");
    chord("Major", "Rmaj13", "<R>Maj7(add13), <R>M7(add13)", "1,3,(5),7,(9),(11),13");
    chord("Major", "R2", "on guitar equivalent to: <R>add9", "1,2,3,5");
    chord("Minor", "Rm", "<R>minor, <R>min, <R>-", "1,b3,5");
    chord("Minor", "Rm6", "<R>minor6, <R>min6", "1,b3,5,6");
    chord("Minor", "Rm6/9", "", "1,b3,(5),6,9");
    chord("Minor", "Rmmaj7", "<R>min/maj7, <R>mM7, <R>m(addM7), <R>m(+7), <R>-(M7)", "1,b3,5,7");
    chord("Minor", "Rmmaj9", "<R>min/maj9, <R>mM9, <R>m(addM9), <R>m(+9), <R>-(M9)", "1,b3,(5),7,9");
    chord("Minor", "Rmadd9", "<R>minor(add9), <R>-(add9)", "1,b3,(5),9");
    chord("Minor Seventh", "Rm7", "<R>minor7, <R>min7, <R>-7", "1,b3,5,b7");
    chord("Minor Seventh", "Rm9", "<R>minor9, <R>min9, <R>-9", "1,b3,(5),b7,9");
    chord("Minor Seventh", "Rm11", "<R>minor11, <R>min11, <R>-11", "1,b3,(5),b7,(9),11");
    chord("Minor Seventh", "Rm13", "<R>minor13, <R>min13, <R>-13", "1,b3,(5),b7,(9),(11),13");
    chord("Diminished", "Rm-5", "<R>m(b5)", "1,b3,b5");
    chord("Diminished", "Rdim", "<R>Ø", "1,b3,b5");
    chord("Diminished", "Rdim7", "<R>Ø7", "1,b3,b5,bb7");
    chord("Half Diminished", "Rm7-5", "<R>°7, <R>½dim, <R>½dim7, <R>m(b7), <R>minor7b5", "1,b3,b5,b7");
    chord("Dominant", "R7", "<R> dominant seventh, <R>dom", "1,3,5,b7");
    chord("Dominant", "R7-9", "<R>7b9, <R>7(add b9)", "1,3,(5),b7,b9");
    chord("Dominant", "R7+9", "<R>7(add#9)", "1,3,(5),b7,#9");
    chord("Dominant", "R7-5", "<R>7b5", "1,3,b5,b7");
    chord("Dominant", "R7+5", "<R>7+, <R>7#5", "1,3,#5,b7");
    chord("Dominant", "R7/6", "<R>7 added 6th", "1,3,(5),6,b7");
    chord("Dominant", "R9", "<R>7(add9)", "1,3,(5),b7,9");
    chord("Dominant", "R9-5", "<R>9b5, <R> ninth flattened 5th", "1,(3),b5,b7,9");
    chord("Dominant", "R9+5", "<R>9#5, <R> ninth augmented 5th", "1,(3),#5,b7,9");
    chord("Dominant", "Radd9", "<R> added 9th, on guitar also: <R>2", "1,3,5,9");
    chord("Dominant", "R9/6", "<R>9 added 6th", "1,(3),(5),6,b7,9");
    chord("Dominant", "R9+11", "<R>9aug11, <R> ninth augmented 11th", "1,3,(5),b7,9,#11");
    chord("Dominant", "R11", "<R>7(add11)", "1,(3),5,b7,(9),11");
    chord("Dominant", "R11-9", "<R>11(b9), <R>11(flattened 9th)", "1,(3),(5),b7,b9,11");
    chord("Dominant", "R13", "<R>7(add13)", "1,(3),5,b7,(9),(11),13");
    chord("Dominant", "R13-9", "<R>13b9", "1,(3),(5),b7,b9,(11),13");
    chord("Dominant", "R13-9-5", "<R>13b9b5", "(1),(3),b5,b7,b9,(11),13");
    chord("Dominant", "R13-9+11", "<R>13b9#11", "(1),(3),(5),b7,b9,#11,13");
    chord("Dominant", "R13+11", "<R>13 augmented 11th", "1,(3),(5),b7,(9),#11,13");
    chord("Dominant", "R7/13", "<R>7/6", "1,3,(5),b7,13");
    chord("Augmented", "Raug", "<R>+, <R>+5, <R>(#5), <R>augmented", "1,3,#5");
    chord("Ambiguous", "Rsus2", "", "1,2,5");
    chord("Ambiguous", "Rsus4", "<R>sus, <R>(sus4)", "1,4,5");
    chord("Ambiguous", "R7sus4", "<R>7sus, <R>7sus11", "1,4,5,b7");
    chord("", "R-9", "<R>b9, <R> flattened 9th", "1,3,(5),b7,b9");
    chord("", "R-9+5", "<R>b9#5, <R> flattened 9th augmented 5th", "1,(3),#5,b7,b9");
    chord("", "R-9+11", "<R>b9#11, <R> flattened 9th augmented 11th", "1,(3),(5),b7,b9,#11");
    chord("", "R-9-5", "<R>b9b5, <R> flattened 9th flattened 5th", "1,(3),b5,b7,b9");
    chord("", "R+5", "<R>aug5, <R> augmented 5th", "1,3,#5");
    chord("", "R+9", "<R>aug9, <R> augmented 9th", "1,3,(5),b7,#9");
    chord("", "R+11", "<R>aug11, <R> augmented 11th", "1,(3),(5),b7,9,#11");
}

function a76() {
    scale("Scale", "::: REGULAR SCALES :::", "", "");
    scale("Scale", "Major", "", "1,2,3,4,5,6,7");
    scale("Scale", "Harmonic Minor", "", "1,2,b3,4,5,b6,7");
    scale("Scale", "Melodic Minor (Ascending)", "", "1,2,b3,4,5,6,7");
    scale("Scale", "Melodic Minor (Descending)", "<R> Natural Minor, <R> Relative Minor", "1,2,b3,4,5,b6,b7");
    scale("Scale","Chromatic","","1,b2,2,b3,3,4,b5,5,#5,6,b7,7");
    scale("Scale","Whole Tone","","1,2,3,#4,#5,b7");
    scale("Scale","Pentatonic Major","","1,2,3,5,6");
    scale("Scale","Pentatonic Minor","","1,b3,4,5,b7");
    scale("Scale","Pentatonic Blues","","1,b3,4,b5,5,b7");
    scale("Scale","Pentatonic Neutral","","1,2,4,5,b7");
    scale("Scale","Octatonic (H-W)","","1,b2,b3,3,b5,5,6,b7");
    scale("Scale","Octatonic (W-H)","","1,2,b3,4,b5,b6,6,7");
    scale("Scale","Ionian","<R> Major","1,2,3,4,5,6,7");
    scale("Scale","Dorian","","1,2,b3,4,5,6,b7");
    scale("Scale","Phrygian","","1,b2,b3,4,5,b6,b7");
    scale("Scale","Lydian","","1,2,3,#4,5,6,7");
    scale("Scale","Mixolydian","","1,2,3,4,5,6,b7");
    scale("Scale","Aeolian","","1,2,b3,4,5,b6,b7");
    scale("Scale","Locrian","","1,b2,b3,4,b5,b6,b7");
    scale("Scale","::: EXOTIC SCALES :::","","");
    scale("Scale","Algerian","","1,2,b3,4,#4,5,b6,7");
    scale("Scale","Arabian (a)","","1,2,b3,4,#4,#5,6,7");
    scale("Scale","Arabian (b)","","1,2,3,4,#4,#5,b7");
    scale("Scale","Augmented","","1,#2,3,#4,#5,7");
    scale("Scale","Auxiliary Diminished","","1,2,b3,4,#4,#5,6,7");
    scale("Scale","Auxiliary Augmented","","1,2,3,#4,#5,#6");
    scale("Scale","Auxiliary Diminished Blues","","1,b2,b3,3,b5,5,6,b7");
    scale("Scale","Balinese","","1,b2,b3,5,b6");
    scale("Scale","Blues","","1,b3,4,#4,5,b7");
    scale("Scale","Byzantine","","1,b2,3,4,5,b6,7");
    scale("Scale","Chinese","","1,3,#4,5,7");
    scale("Scale","Chinese Mongolian","","1,2,3,5,6");
    scale("Scale","Diatonic","","1,2,3,5,6");
    scale("Scale","Diminished","","1,2,b3,4,b5,b6,6,7");
    scale("Scale","Diminished, Half","","1,b2,b3,3,b5,5,6,b7");
    scale("Scale","Diminished, Whole","","1,2,b3,4,b5,b6,6,7");
    scale("Scale","Diminished Whole Tone","","1,b2,b3,3,b5,b6,b7");
    scale("Scale","Dominant 7th","","1,2,3,4,5,6,b7");
    scale("Scale","Double Harmonic","","1,b2,3,4,5,b6,7");
    scale("Scale","Egyptian","","1,2,4,5,b7");
    scale("Scale","Eight Tone Spanish","","1,b2,#2,3,4,b5,b6,b7");
    scale("Scale","Enigmatic","","1,b2,3,#4,#5,#6,7");
    scale("Scale","Ethiopian (A raray)","","1,2,3,4,5,6,7");
    scale("Scale","Ethiopian (Geez & Ezel)","","1,2,b3,4,5,b6,b7");
    scale("Scale","Half Diminished (Locrian)","","1,b2,b3,4,b5,b6,b7");
    scale("Scale","Half Diminished #2 (Locrian #2)","","1,2,b3,4,b5,b6,b7");
    scale("Scale","Hawaiian","","1,2,b3,4,5,6,7");
    scale("Scale","Hindu","","1,2,3,4,5,b6,b7");
    scale("Scale","Hindustan","","1,2,3,4,5,b6,b7");
    scale("Scale","Hirajoshi","","1,2,b3,5,b6");
    scale("Scale","Hungarian Major","","1,#2,3,#4,5,6,b7");
    scale("Scale","Hungarian Gypsy","","1,2,b3,#4,5,b6,7");
    scale("Scale","Hungarian Gypsy Persian","","1,b2,3,4,5,b6,7");
    scale("Scale","Hungarian Minor","","1,2,b3,#4,5,b6,7");
    scale("Scale","Japanese (A)","","1,b2,4,5,b6");
    scale("Scale","Japanese (B)","","1,2,4,5,b6");
    scale("Scale","Japanese (Ichikosucho)","","1,2,3,4,#4,5,6,7");
    scale("Scale","Japanese (Taishikicho)","","1,2,3,4,#4,5,6,#6,7");
    scale("Scale","Javaneese","","1,b2,b3,4,5,6,b7");
    scale("Scale","Jewish (Adonai Malakh)","","1,b2,2,b3,4,5,6,b7");
    scale("Scale","Jewish (Ahaba Rabba)","","1,b2,3,4,5,b6,b7");
    scale("Scale","Jewish (Magen Abot)","","1,b2,#2,3,#4,#5,#6,7");
    scale("Scale","Kumoi","","1,2,b3,5,6");
    scale("Scale","Leading Whole Tone","","1,2,3,#4,#5,#6,7");
    scale("Scale","Lydian Augmented","","1,2,3,#4,#5,6,7");
    scale("Scale","Lydian Minor","","1,2,3,#4,5,b6,b7");
    scale("Scale","Lydian Diminished","","1,2,b3,#4,5,6,7");
    scale("Scale","Major Locrian","","1,2,3,4,b5,b6,b7");
    scale("Scale","Mela Bhavapriya (44)","","1,b2,2,4,5,#5,6");
    scale("Scale","Mela Chakravakam (16)","","1,b2,3,4,5,6,b7");
    scale("Scale","Mela Chalanata (36)","","1,#2,3,4,5,#6,7");
    scale("Scale","Mela Charukesi (26)","","1,2,3,4,5,b6,b7");
    scale("Scale","Mela Chitrambari (66)","","1,2,3,#4,5,#6,7");
    scale("Scale","Mela Dharmavati (59)","","1,2,b3,#4,5,6,7");
    scale("Scale","Mela Dhatuvardhani (69)","","1,#2,3,#4,5,b6,7");
    scale("Scale","Mela Dhavalambari (49)","","1,b2,3,#4,5,#5,6");
    scale("Scale","Mela Dhenuka (9)","","1,b2,b3,4,5,b6,7");
    scale("Scale","Mela Dhirasankarabharana (29)","","1,2,3,4,5,6,7");
    scale("Scale","Mela Divyamani (48)","","1,b2,b3,#4,5,#6,7");
    scale("Scale","Mela Gamanasrama (53)","","1,b2,3,#4,5,6,7");
    scale("Scale","Mela Ganamurti (3)","","1,b2,2,4,5,b6,7");
    scale("Scale","Mela Gangeyabhusani (33)","","1,#2,3,4,5,b6,7");
    scale("Scale","Mela Gaurimanohari (23)","","1,2,b3,4,5,6,7");
    scale("Scale","Mela Gavambodhi (43)","","1,b2,b3,#4,5,#5,6");
    scale("Scale","Mela Gayakapriya (13)","","1,b2,3,4,5,#5,6");
    scale("Scale","Mela Hanumattodi (8)","","1,b2,b3,4,5,b6,b7");
    scale("Scale","Mela Harikambhoji (28)","","1,2,3,4,5,6,b7");
    scale("Scale","Mela Hatakambari (18)","","1,b2,3,4,5,#6,7");
    scale("Scale","Mela Hemavati (58)","","1,2,b3,#4,5,6,b7");
    scale("Scale","Mela Jalarnavam (38)","","1,b2,2,#4,5,b6,b7");
    scale("Scale","Mela Jhalavarali (39)","","1,b2,2,#4,5,6,b7");
    scale("Scale","Mela Jhankaradhvani (19)","","1,2,b3,4,5,#6,5,6");
    scale("Scale","Mela Jyotisvarupini (68)","","1,#2,3,#4,5,b6,b7");
    scale("Scale","Mela Kamavardhani (51)","","1,b2,3,#4,5,b6,7");
    scale("Scale","Mela Kanakangi (1)","","1,b2,2,4,5,#5,6");
    scale("Scale","Mela Kantamani (61)","","1,2,3,#4,5,#5,6");
    scale("Scale","Mela Kharaharapriya (22)","","1,2,b3,4,5,6,b7");
    scale("Scale","Mela Kiravani (21)","","1,2,b3,4,5,b6,7");
    scale("Scale","Mela Kokilapriya (11)","","1,b2,b3,4,5,6,7");
    scale("Scale","Mela Kosalam (71)","","1,#2,3,#4,5,6,7");
    scale("Scale","Mela Latangi (63)","","1,2,3,#4,5,b6,7");
    scale("Scale","Mela Manavati (5)","","1,b2,2,4,5,6,7");
    scale("Scale","Mela Mararanjani (25)","","1,2,3,4,5,#5,6");
    scale("Scale","Mela Mayamalavagaula (15)","","1,b2,3,4,5,#5,6");
    scale("Scale","Mela Mechakalyani (65)","","1,2,3,#4,5,6,7");
    scale("Scale","Mela Naganandini (30)","","1,2,3,4,5,#6,7");
    scale("Scale","Mela Namanarayani (50)","","1,b2,3,#4,5,b6,b7");
    scale("Scale","Mela Nasikabhusani (70)","","1,#2,3,#4,5,6,b7");
    scale("Scale","Mela Natabhairavi (20)","","1,2,b3,4,5,b6,b7");
    scale("Scale","Mela Natakapriya (10)","","1,b2,b3,4,5,6,b7");
    scale("Scale","Mela Navanitam (40)","","1,b2,2,#4,5,6,b7");
    scale("Scale", "Mela Nitimati (60)", "", "1,2,b3,#4,5,#6,7");
    scale("Scale", "Mela Pavani (41)", "", "1,b2,2,#4,5,6,7");
    scale("Scale", "Mela Ragavardhani (32)", "", "1,#2,3,4,5,b6,b7");
    scale("Scale", "Mela Raghupriya (42)", "", "1,b2,2,#4,5,#6,7");
    scale("Scale", "Mela Ramapriya (52)", "", "1,b2,3,#4,5,6,b7");
    scale("Scale", "Mela Rasikapriya (72)", "", "1,#2,3,#4,5,#6,7");
    scale("Scale", "Mela Ratnangi (2)", "", "1,b2,2,4,5,b6,b7");
    scale("Scale", "Mela Risabhapriya (62)", "", "1,2,3,#4,5,b6,b7");
    scale("Scale", "Mela Rupavati (12)", "", "1,b2,b3,4,5,#6,7");
    scale("Scale", "Mela Sadvidhamargini (46)", "", "1,b2,b3,#4,5,6,b7");
    scale("Scale", "Mela Salagam (37)", "", "1,b2,2,#4,5,#5,6");
    scale("Scale", "Mela Sanmukhapriya (56)", "", "1,2,b3,#4,5,b6,b7");
    scale("Scale", "Mela Sarasangi (27)", "", "1,2,3,4,5,b6,7");
    scale("Scale", "Mela Senavati (7)", "", "1,b2,b3,4,5,#5,6");
    scale("Scale", "Mela Simhendramadhyama (57)", "", "1,2,b3,#4,5,b6,7");
    scale("Scale", "Mela Subhapantuvarali (45)", "", "1,b2,b3,#4,5,b6,7");
    scale("Scale", "Mela Sucharitra (67)", "", "1,#2,3,#4,5,#5,6");
    scale("Scale", "Mela Sulini (35)", "", "1,#2,3,4,5,6,7");
    scale("Scale", "Mela Suryakantam (17)", "", "1,b2,3,4,5,6,7");
    scale("Scale", "Mela Suvarnangi (47)", "", "1,b2,2,#4,5,6,7");
    scale("Scale", "Mela Syamalangi (55)", "", "1,2,b3,#4,5,#5,6");
    scale("Scale", "Mela Tanarupi (6)", "", "1,b2,2,4,5,#6,7");
    scale("Scale", "Mela Vaschaspati (64)", "", "1,2,3,#4,5,6,b7");
    scale("Scale", "Mela Vagadhisvari (34)", "", "1,#2,3,4,5,6,b7");
    scale("Scale", "Mela Vakulabharanam (14)", "", "1,#2,3,4,5,b6,b7");
    scale("Scale", "Mela Vanaspati (4)", "", "1,b2,2,4,5,6,b7");
    scale("Scale", "Mela Varunapriya (24)", "", "1,2,b3,4,5,#6,7");
    scale("Scale", "Mela Visvambari (54)", "", "1,#2,3,#4,5,#6,7");
    scale("Scale", "Mela Yagapriya (31)", "", "1,#2,3,4,5,#5,6");
    scale("Scale", "Mohammedan", "", "1,2,b3,4,5,b6,7");
    scale("Scale", "Neopolitan", "", "1,b2,b3,4,5,b6,7");
    scale("Scale", "Neoploitan Major", "", "1,b2,b3,4,5,6,7");
    scale("Scale", "Neopolitan Minor", "", "1,b2,b3,4,5,b6,b7");
    scale("Scale", "Nine Tone Scale", "", "1,2,#2,3,#4,5,#5,6,7");
    scale("Scale", "Oriental (a)", "", "1,b2,3,4,b5,b6,b7");
    scale("Scale", "Oriental (b)", "", "1,b2,3,4,b5,6,b7");
    scale("Scale", "Overtone", "", "1,2,3,#4,5,6,b7");
    scale("Scale", "Overtone Dominant", "", "1,2,3,#4,5,6,b7");
    scale("Scale", "Pelog", "", "1,b2,b3,5,b6");
    scale("Scale", "Persian", "", "1,b2,3,4,b5,b6,7");
    scale("Scale", "Prometheus", "", "1,2,3,b5,6,b7");
    scale("Scale", "Prometheus Neopolitan", "", "1,b2,3,b5,6,b7");
    scale("Scale", "Pure Minor", "", "1,2,b3,4,5,b6,b7");
    scale("Scale", "Purvi Theta", "", "1,b2,3,#4,5,b6,7");
    scale("Scale", "Roumanian Minor", "", "1,2,b3,#4,5,6,b7");
    scale("Scale", "Six Tone Symmetrical", "", "1,b2,3,4,#5,6");
    scale("Scale", "Spanish Gypsy", "", "1,b2,3,4,5,b6,b7");
    scale("Scale", "Super Locrian", "", "1,b2,#2,3,#4,#5,b7");
    scale("Scale", "Theta, Asavari", "", "1,2,b3,4,5,b6,b7");
    scale("Scale", "Theta, Bilaval", "", "1,2,3,4,5,6,7");
    scale("Scale", "Theta, Bhairav", "", "1,b2,3,4,5,b6,7");
    scale("Scale", "Theta, Bhairavi", "", "1,b2,b3,4,5,b6,b7");
    scale("Scale", "Theta, Kafi", "", "1,2,b3,4,5,6,b7");
    scale("Scale", "Theta, Kalyan", "", "1,2,3,#4,5,6,7");
    scale("Scale", "Theta, Khamaj", "", "1,2,3,4,5,6,b7");
    scale("Scale", "Theta, Marva", "", "1,b2,3,#4,5,6,7");
    scale("Scale", "Todi Theta", "", "1,b2,b3,#4,5,b6,7");
}

function showFingerSetting(mode) {
    mde = mode;
    c41 = "";
    var noteName = "";
    var a33 = "";
    var sep = "|";
    var a85 = -1;
    var a84 = 0;
    var b53 = 0;
    var a23 = "";
    var a13 = "";
    var a54 = "";

    if (mde == "Chords") {
        if (document.formChordsAndScales.chordRoot.selectedIndex == -1)
            document.formChordsAndScales.chordRoot.selectedIndex = document.formChordsAndScales.scaleRoot.selectedIndex;
        if (document.formChordsAndScales.chordName.selectedIndex == -1)
            document.formChordsAndScales.chordName.selectedIndex = c10;

        document.formChordsAndScales.scaleRoot.selectedIndex = -1;
        document.formChordsAndScales.scaleName.selectedIndex = -1;
        noteName = document.formChordsAndScales.chordRoot.options[document.formChordsAndScales.chordRoot.selectedIndex].value;
        a33 = document.formChordsAndScales.chordName.options[document.formChordsAndScales.chordName.selectedIndex].value;

        a23 = Chords["R" + a33];
        c10 = document.formChordsAndScales.chordName.selectedIndex;
    }
    else if (mde == "Scales") {
        if (document.formChordsAndScales.scaleRoot.selectedIndex == -1)
            document.formChordsAndScales.scaleRoot.selectedIndex = document.formChordsAndScales.chordRoot.selectedIndex;
        if (document.formChordsAndScales.scaleName.selectedIndex == -1)
            document.formChordsAndScales.scaleName.selectedIndex = c11;

        document.formChordsAndScales.chordRoot.selectedIndex = -1;
        document.formChordsAndScales.chordName.selectedIndex = -1;

        noteName = document.formChordsAndScales.scaleRoot.options[document.formChordsAndScales.scaleRoot.selectedIndex].value;
        a33 = document.formChordsAndScales.scaleName.options[document.formChordsAndScales.scaleName.selectedIndex].value;

        if (a33.charAt(0) == ":") {
            if (document.formChordsAndScales.scaleName.selectedIndex != 0 && document.formChordsAndScales.scaleName.selectedIndex == c11 - 1) {
                document.formChordsAndScales.scaleName.selectedIndex--;
            }
            else {
                document.formChordsAndScales.scaleName.selectedIndex++;
            }

            a33 = document.formChordsAndScales.scaleName.options[document.formChordsAndScales.scaleName.selectedIndex].value;
        }

        a23 = a73[a33];
        c11 = document.formChordsAndScales.scaleName.selectedIndex;
    }
    else
        alert("ERROR: `infoType' error.\nPlease contact Erik van der Neut via the Chord House forum.\ninfoType = " + mde + "\nThank you.");

    a84 = a23.indexOf(sep);

    while (a84 != -1) {
        b53++;
        if (b53 == 1) {
            a13 = a23.substring(a85 + 1, a84);
        }
        else if (b53 == 2) {
            a54 = a23.substring(a85 + 1, a84);
        }
        else {
        }
        a85 = a84;
        a84 = a23.indexOf(sep, a85 + 1);
    }

    if (root_disambig_sharp_or_flat == "b") {
        if (noteName == "C#")  noteName = "Db";
        else if (noteName == "D#") noteName = "Eb";
        else if (noteName == "F#") noteName = "Gb";
        else if (noteName == "G#") noteName = "Ab";
        else if (noteName == "A#") noteName = "Bb";
    }

    a67(noteName, a33, a23.substring(a85 + 1, a23.length), a13, a54);
}

function b01(b07) {
    var b00 = new Array("C", "Cis", "D", "Dis", "E", "F", "Fis", "G", "Gis", "A", "Ais", "B");
    var music_note = b00[b07 % 12];
    music_note += "_";
    music_note += Math.floor((b07 / 12) - 1);
    return music_note;
}

function a67(noteName, a15, interval, a13, a54) {
    var sep = ",";
    var a85 = -1;
    var a84 = 0;
    var i = 0;
    var a30 = "";
    var b09 = true;
    var base_C = 48;
    document.midi.music_notes.value = "";
    for (i = 1; i <= 36; i++)
        clear(i);
    a84 = interval.indexOf(sep);
    while (b09) {
        if (a84 != -1)
            a30 = interval.substring(a85 + 1, a84);
        else {
            a30 = interval.substring(a85 + 1, interval.length);
            b09 = false;
        }

        if (a30.charAt(0) == "(") {
            a30 = a30.substring(1, a30.length - 1);
            a82(noteOffset[noteName] + halfTones[a30], c23(noteName, a30, displayNames));
            c41 += ("(" + c23(noteName, a30, "nn") + "),");
        }
        else {
            a83(noteOffset[noteName] + halfTones[a30], c23(noteName, a30, displayNames));
            c41 += (c23(noteName, a30, "nn") + ",");

            if (document.midi.music_notes.value != "")
                document.midi.music_notes.value += ",";

            document.midi.music_notes.value += b01(base_C + noteOffset[noteName] - 1 + halfTones[a30]);
        }

        if (a84 != -1) {
            a85 = a84;
            a84 = interval.indexOf(sep, a85 + 1);
        }
    }

    a54 = a98("<R>", noteName, a54);
    document.midi.full_name.value = noteName + " " + a15;

    if (a13.toLowerCase() == "scale") {
        document.midi.playstyle.value = "piano_scale";
        document.midi.statusmsgplay.value = "Hear this scale...";
        document.midi.statusmsgsave.value = "Save the sound file for this scale...";
        document.formChordsAndScales.infoText.value = "            " + noteName + " " + a15 + " Scale\n";
    }
    else {
        document.midi.playstyle.value = "piano_chord";
        document.midi.statusmsgplay.value = "Hear this chord...";
        document.midi.statusmsgsave.value = "Save the sound file for this chord...";
        document.formChordsAndScales.infoText.value = "            " + noteName + a15;
        document.formChordsAndScales.infoText.value += "\n";
    }

    if (a54 != "")
        document.formChordsAndScales.infoText.value += "a.k.a.:     " + a54 + "\n";

    document.formChordsAndScales.infoText.value += "intervals:  " + interval + "\n" + "half-steps: ";
    c12(interval, a13);
    for (var i = 0; i < c08.length; i++){
        document.formChordsAndScales.infoText.value += c08[i];
        if (i < c08.length - 1)
            document.formChordsAndScales.infoText.value += "-";
    }

    if (c09.length > 0) {
        document.formChordsAndScales.infoText.value += " (excl. optional notes)\nhalf-steps: ";
        for (var i = 0; i < c09.length; i++){
            document.formChordsAndScales.infoText.value += c09[i];
            if (i < c09.length - 1)
                document.formChordsAndScales.infoText.value += "-";
        }

        document.formChordsAndScales.infoText.value += " (incl. optional notes)";
    }

    document.formChordsAndScales.infoText.value += "\n";
    c41 = c41.substring(0, c41.length - 1);
    document.formChordsAndScales.infoText.value += "notes:      " + c41;
}

function c12(intervl, mode) {
    var c26 = intervl.split(",");
    var num1 = 0;
    var num2 = 0;
    var a87 = false;
    var i = 0;
    var j = 0;
    c08 = [];
    c09 = [];
    for (i = 1; i < c26.length; i++){
        if (c26[i].charAt(0) != "(") {
            num2 = halfTones[c26[i]];
            c08[j] = num2 - num1;
            num1 = num2;
            j++;
        }
        else
            a87 = true;
    }
    if (mode.toLowerCase() == "scale") {
        c08[i - 1] = 12 - num1;
    }
    num1 = 0;
    num2 = 0;
    if (a87) {
        for (var i = 1; i < c26.length; i++){
            if (c26[i].charAt(0) == "(") {
                num2 = halfTones[c26[i].substring(1, c26[i].length - 1)];
            }
            else {
                num2 = halfTones[c26[i]];
            }
            c09[i - 1] = num2 - num1;
            num1 = num2;
        }

        if (mode.toLowerCase() == "scale") {
            c09[i - 1] = 12 - num1;
        }
    }
}

function a83(a96, c42) {
    if (c42.substring(1, c42.length) == "##") {
        c42 = a98("##", "x", c42);
    }
    else if (c42.substring(0, 2) == "bb" || c42.substring(1, c42.length) == "bb") {
        c42 = a98("bb", "<span class=\"bb\">bb</span>", c42);
    }

    document["k" + a96].src = eval("i" + a96 + "p.src");
    c18("n" + a96, c42);
}

function a82(a96, c42) {
    if (c42.substring(1, c42.length) == "##") {
        c42 = a98("##", "x", c42);
    }
    else if (c42.substring(0, 2) == "bb" || c42.substring(1, c42.length) == "bb") {
        c42 = a98("bb", "<span class=\"bb\">bb</span>", c42);
    }

    document["k" + a96].src = eval("i" + a96 + "o.src");
    c18("n" + a96, c42);
}

function clear(a96) {
    document["k" + a96].src = eval("i" + a96 + ".src");
    c18("n" + a96, "");
}

function c23(noteName, c21, settingInOrNN) {
    if (settingInOrNN == "in")
        return c21;
    var c22 = c21.substring(c17(c21));
    var c20 = c21.substring(0, c17(c21));
    if (c22 > 7) c22 -= 7;
    var c14 = c38[noteName][c22 - 1];
    for (var i = 0; i < c20.length; i++){
        if (c20.charAt(i) == 'b')
            c14 = c15(c14);
        else if (c20.charAt(i) == '#')
            c14 = c16(c14);
        else {
            alert("ERROR: unexpected character in `interval_adjustment'.\nPlease report this error to Erik van der Neut via the Chord House forum.");
        }
    }
    if (c13 == "SIMPLE") {
        if (c14 == "Cbb") c14 = "Bb";
        else if (c14 == "A##" || c14 == "Cb") c14 = "B";
        else if (c14 == "B#" || c14 == "Dbb") c14 = "C";
        else if (c14 == "B##") c14 = "C#";
        else if (c14 == "C##" || c14 == "Ebb") c14 = "D";
        else if (c14 == "Fbb") c14 = "Eb";
        else if (c14 == "D##" || c14 == "Fb") c14 = "E";
        else if (c14 == "E#" || c14 == "Gbb") c14 = "F";
        else if (c14 == "E##") c14 = "F#";
        else if (c14 == "F##" || c14 == "Abb") c14 = "G";
        else if (c14 == "G##" || c14 == "Bbb") c14 = "A";
    }

    return c14;
}

function c15(c14) {
    if (c14.length == 1) c14 += "b";
    else if (c14.charAt(c14.length - 1) == "#") c14 = c14.substring(0, c14.length - 1);
    else if (c14.substring(c14.length - 2) == "bb") {
        if (c14 == "Cbb" && c13 == "STRICT") c14 = "Bbb";
        else if (c14 == "Cbb" && c13 == "SIMPLE") c14 = "A";
        else if (c14 == "Bbb") c14 = "Ab";
        else if (c14 == "Abb") c14 = "Gb";
        else if (c14 == "Gbb") c14 = "Fb";
        else if (c14 == "Fbb" && c13 == "STRICT") c14 = "Ebb";
        else if (c14 == "Fbb" && c13 == "SIMPLE") c14 = "D";
        else if (c14 == "Ebb") c14 = "Db";
        else if (c14 == "Dbb") c14 = "Cb";
    }
    else if (c14.charAt(c14.length - 1) == "b" && c13 == "STRICT") c14 = c14 + "b";
    else if (c14.charAt(c14.length - 1) == "b" && c13 == "SIMPLE") {
        if (c14 == "Cb") c14 = "Bb";
        else if (c14 == "Bb") c14 = "A";
        else if (c14 == "Ab") c14 = "G";
        else if (c14 == "Gb") c14 = "F";
        else if (c14 == "Fb") c14 = "Eb";
        else if (c14 == "Eb") c14 = "D";
        else if (c14 == "Db") c14 = "C";
    }
    else {
        alert("ERROR: Don't know how to apply function `lower_one_half_step' on value '" + c14 + "'.\nPlease report this error to Erik van der Neut via the Chord House forum");
    }
    return c14;
}

function c16(c14) {
    if (c14.length == 1) c14 += "#";
    else if (c14.charAt(c14.length - 1) == "b") c14 = c14.substring(0, c14.length - 1);
    else if (c14.substring(c14.length - 2) == "##") {
        if (c14 == "E##" && c13 == "STRICT") c14 = "F##";
        else if (c14 == "E##" && c13 == "SIMPLE") c14 = "G";
        else if (c14 == "F##") c14 = "G#";
        else if (c14 == "G##") c14 = "A#";
        else if (c14 == "A##") c14 = "B#";
        else if (c14 == "B##" && c13 == "STRICT") c14 = "C##";
        else if (c14 == "B##" && c13 == "SIMPLE") c14 = "D";
        else if (c14 == "C##") c14 = "D#";
        else if (c14 == "D##") c14 = "E#";
    }
    else if (c14.charAt(c14.length - 1) == "#" && c13 == "STRICT") c14 = c14 + "#";
    else if (c14.charAt(c14.length - 1) == "#" && c13 == "SIMPLE") {
        if (c14 == "C#") c14 = "D";
        else if (c14 == "D#") c14 = "E";
        else if (c14 == "E#") c14 = "F#";
        else if (c14 == "F#") c14 = "G";
        else if (c14 == "G#") c14 = "A";
        else if (c14 == "A#") c14 = "B";
        else if (c14 == "B#") c14 = "C#";
    }
    else {
        alert("ERROR: Don't know how to apply function `raise_one_half_step' on value '" + c14 + "'.\nPlease report this error to vanderneut@looknohands.com");
    }
    return c14;
}

function c17(str) {
    var intstring = "0123456789";
    for (var i = 0; i < str.length; i++){
        if (intstring.indexOf(str.charAt(i)) != -1)
            return i;
    }
    return -1;
}

function c18(id, str) {
    if (document.getElementById) {
        document.getElementById(id).innerHTML = str;
    }
    else if (document.all) {
        document.all[id].innerHTML = str;
    }
    else if (document.layers) {
        with (document[id].document) {
            open();
            write(str);
            close();
        }
    }
}

function a98(a28, a32, a17) {
    var a85 = -1;
    var a84 = 0;
    var result = "";
    var b09 = true;
    while (b09) {
        a84 = a17.indexOf(a28, a85 + 1);
        if (a84 != -1) {
            result += a17.substring(a85 + 1, a84) + a32;
            a85 = a84 + a28.length - 1;
        }
        else {
            result += a17.substring(a85 + 1, a17.length);
            b09 = false;
        }
    }
    return result;
}

function a99(c19, element, sep) {
    var a85 = -1;
    var a84 = 0;
    a84 = c19.indexOf(sep);
    while (a84 != -1) {
        if (element == c19.substring(a85 + 1, a84)) {
            return a85 + 1;
        }
        a85 = a84;
        a84 = c19.indexOf(sep, a85 + 1);
    }

    if (element == c19.substring(a85 + 1, c19.length))
        return a85 + 1;
    else
        return -1;
}