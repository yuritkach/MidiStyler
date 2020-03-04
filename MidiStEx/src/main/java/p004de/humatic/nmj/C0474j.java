package p004de.humatic.nmj;

import android.support.p000v4.internal.view.SupportMenu;

/* renamed from: de.humatic.nmj.j */
class C0474j {
    /* renamed from: a */
    public void mo8142a(int i, C0467g gVar, int i2) {
        try {
            String str = gVar.f281c;
            if (i >= 32 || str != null) {
                String str2 = gVar.f282d;
                int i3 = gVar.f279b;
                String str3 = gVar.f278a;
                if (i == 1 || i == 2 || i == 6) {
                    String str4 = i == 1 ? "RTP" : i == 2 ? "Bluetooth" : "WebSocket";
                    int numChannels = NMJConfig.getNumChannels();
                    int i4 = 0;
                    while (i4 < numChannels) {
                        if (NMJConfig.getIP(i4) == null || !NMJConfig.getIP(i4).equalsIgnoreCase(str) || NMJConfig.getPort(i4) != i3) {
                            i4++;
                        } else {
                            C0484p.logln(7, "rediscovered " + str4 + " channel " + str + " " + i3);
                            if ((NMJConfig.getRTPState(i4) & 96) == 0) {
                                boolean z = false;
                                if (!str3.equalsIgnoreCase(NMJConfig.getName(i4)) && !NetworkMidiSystem.get().isOpen(-1, i4)) {
                                    NMJConfig.setName(i4, str3);
                                    z = true;
                                }
                                if (i != 2 && !NetworkMidiSystem.get().isOpen(-1, i4)) {
                                    z = NMJConfig.m84b(i4, i2);
                                }
                                if (z) {
                                    NMJConfig.m59a(i4, 4, 8);
                                }
                                if (NMJConfig.getRTPState(i4) != 16) {
                                    NMJConfig.m59a(i4, 4, 16);
                                    NMJConfig.m84b(i4, 16);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                    }
                    if (NMJConfig.m82b() >= 4) {
                        String b = NMJConfig.m83b(i2);
                        C0484p.logln(4, "DNS (" + b + "), serviceDiscovered " + str + ":" + i3);
                        C0484p.logln(4, "Adding " + b + " / " + str4 + " channel: " + str3);
                    }
                    if (NMJConfig.m38a() != null) {
                        NMJConfig.m38a().putInt("numCh", numChannels + 1);
                        NMJConfig.m38a().commit();
                    }
                    NMJConfig.m40a(numChannels, i);
                    NMJConfig.m84b(numChannels, 16);
                    NMJConfig.setIP(numChannels, str);
                    NMJConfig.setPort(numChannels, i3);
                    if (str3.length() > 0) {
                        NMJConfig.setName(numChannels, str3);
                    }
                    if (i == 1 || i == 6) {
                        NMJConfig.m84b(numChannels, i2);
                    }
                    NMJConfig.m59a(numChannels, 4, 8);
                } else if (i == 5 || i == 7) {
                    int parseInt = Integer.parseInt(str2);
                    int i5 = (parseInt >> 16) & SupportMenu.USER_MASK;
                    int i6 = 65535 & parseInt;
                    int numChannels2 = NMJConfig.getNumChannels();
                    for (int i7 = 0; i7 < numChannels2; i7++) {
                        if (NMJConfig.getMode(i7) == i) {
                            int localPort = (NMJConfig.getLocalPort(i7) >> 16) & SupportMenu.USER_MASK;
                            int localPort2 = NMJConfig.getLocalPort(i7) & SupportMenu.USER_MASK;
                            if (localPort == i5 && localPort2 == i6 && (i == 7 || NMJConfig.getPort(i7) == (i3 & 255))) {
                                if (NMJConfig.getRTPState(i7) != 32 && NMJConfig.getRTPState(i7) != 16) {
                                    NMJConfig.m59a(i7, 4, 16);
                                    NMJConfig.m84b(i7, 16);
                                    NMJConfig.m98c();
                                    return;
                                }
                                return;
                            }
                        }
                    }
                    C0484p.logln(4, "adding USB channel " + str3);
                    NMJConfig.m38a().putInt("numCh", numChannels2 + 1);
                    NMJConfig.m38a().commit();
                    if (str3.length() > 0) {
                        NMJConfig.setName(numChannels2, str3);
                    }
                    NMJConfig.m40a(numChannels2, i);
                    NMJConfig.setIP(numChannels2, str);
                    int i8 = i3 >> 8;
                    if (i8 == 3) {
                        try {
                            NMJConfig.setIO(numChannels2, -1);
                        } catch (Exception e) {
                        }
                    } else if (i8 == 2) {
                        NMJConfig.setIO(numChannels2, 1);
                    } else if (i8 == 1) {
                        NMJConfig.setIO(numChannels2, 0);
                    }
                    NMJConfig.setPort(numChannels2, i3 & 255);
                    NMJConfig.setLocalPort(numChannels2, Integer.parseInt(str2));
                    NMJConfig.m98c();
                    NMJConfig.m84b(numChannels2, 16);
                    NMJConfig.m59a(numChannels2, 4, 8);
                } else if (i == 32) {
                    int numChannels3 = NMJConfig.getNumChannels();
                    int i9 = 0;
                    while (i9 < numChannels3) {
                        if (NMJConfig.getMode(i9) != 32 || ((gVar.f281c != null || !gVar.f278a.equalsIgnoreCase(NMJConfig.getName(i9))) && (NMJConfig.getIP(i9) == null || !NMJConfig.getIP(i9).equalsIgnoreCase(gVar.f281c) || NMJConfig.getPort(i9) != gVar.f279b))) {
                            i9++;
                        } else if (gVar.f277a == -1) {
                            NMJConfig.m59a(i9, 4, 512);
                            return;
                        } else {
                            return;
                        }
                    }
                    if (gVar.f281c != null || gVar.f282d != null) {
                        NMJConfig.m38a().putInt("numCh", numChannels3 + 1);
                        NMJConfig.m38a().commit();
                        NMJConfig.setName(numChannels3, gVar.f278a);
                        NMJConfig.m40a(numChannels3, i);
                        NMJConfig.setIP(numChannels3, gVar.f281c);
                        NMJConfig.setPort(numChannels3, gVar.f279b);
                        NMJConfig.m59a(numChannels3, 4, 8);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo8141a(int i, int i2) {
        if (i == 5) {
            if (i2 == 1 || i2 == 2) {
                if ((NMJConfig.getFlags(-1) & 32) == 0 || (NMJConfig.getFlags(-1) & 256) != 0) {
                    if (NMJConfig.m38a()) {
                        NMJConfig.m59a(-1, (int) NMJConfig.EV_QUERY_USB, i2);
                    }
                    NMJConfig.f94l = false;
                    return;
                }
                NMJConfig.m88b(false);
            } else if (i2 == 0) {
                if (!NMJConfig.m38a()) {
                    NMJConfig.m59a(-1, (int) NMJConfig.EV_QUERY_USB, 0);
                }
                NMJConfig.f94l = true;
            }
        } else if (i == 7) {
            if (i2 == 1 || i2 == 2) {
                if (NMJConfig.m38a()) {
                    NMJConfig.m59a(-1, (int) NMJConfig.EV_QUERY_USB, i2);
                }
                NMJConfig.f94l = false;
            } else if (i2 == 0) {
                if (!NMJConfig.m38a()) {
                    NMJConfig.m59a(-1, (int) NMJConfig.EV_QUERY_USB, 0);
                }
                NMJConfig.f94l = true;
            }
        } else if (i == 2) {
            NMJConfig.m59a(-1, 528, i2);
        }
    }

    C0474j() {
    }
}
