package com.example.project;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import krpc.client.Connection;
import krpc.client.RPCException;
import krpc.client.services.SpaceCenter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView q = findViewById(R.id.data);
        try {
            // change address depending on where the server is thats being called as well as ports
            // (calling requires a lot to be done on the server end)
            Connection connection = Connection.newInstance("app", "193.73.88.30", 50000, 50001);
            //setup for controlling craft
            SpaceCenter spaceCenter = SpaceCenter.newInstance(connection);
            final SpaceCenter.Vessel vessel = spaceCenter.getActiveVessel();
            final SpaceCenter.Control controlling = vessel.getControl();
            controlling.setInputMode(SpaceCenter.ControlInputMode.OVERRIDE);
            // say it works
            q.setVisibility(View.VISIBLE);
            q.setText("connected");
            //BUTTONS
            final Button brakes = findViewById(R.id.bBrakes);
            final Button gear = findViewById(R.id.bGear);
            final Button stage = findViewById(R.id.bStage);
            final Button pUp = findViewById(R.id.pUP);
            final Button pDown = findViewById(R.id.pDown);
            final Button yLeft = findViewById(R.id.yawLeft);
            final Button yRight = findViewById(R.id.yawRight);
            final Button rLeft = findViewById(R.id.rollLeft);
            final Button rRight = findViewById(R.id.rollRight);
            final Switch sas = findViewById(R.id.sSAS);
            final SeekBar thrust = findViewById(R.id.thrust);

            //what the buttons do
            brakes.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch (View v, MotionEvent e) {
                    if (e.getAction() == MotionEvent.ACTION_DOWN) {
                        try {
                            controlling.setBrakes(true);
                        } catch (RPCException q) {
                            q.printStackTrace();
                        }
                    } else if (e.getAction() == MotionEvent.ACTION_UP) {
                        try {
                            controlling.setBrakes(false);
                        } catch (RPCException q) {
                            q.printStackTrace();
                        }
                    }
                    return false;
                }
            });
            gear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        controlling.setGear(!controlling.getGear());
                        System.out.println("gear");
                    } catch (RPCException e) {
                        e.printStackTrace();
                    }
                }
            });
            stage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        controlling.activateNextStage();
                        System.out.println("stage");
                    } catch (RPCException e) {
                        e.printStackTrace();
                    }
                }
            });
            pDown.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch (View v, MotionEvent e) {
                    if (e.getAction() == MotionEvent.ACTION_DOWN) {
                        try {
                            controlling.setPitch(-1);
                        } catch (RPCException q) {
                            q.printStackTrace();
                        }
                    } else if (e.getAction() == MotionEvent.ACTION_UP) {
                        try {
                            controlling.setPitch(0);
                        } catch (RPCException q) {
                            q.printStackTrace();
                        }
                    }
                    return false;
                }
            });
            pUp.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch (View v, MotionEvent e) {
                    if (e.getAction() == MotionEvent.ACTION_DOWN) {
                        try {
                            controlling.setPitch(1);
                        } catch (RPCException q) {
                            q.printStackTrace();
                        }
                    } else if (e.getAction() == MotionEvent.ACTION_UP) {
                        try {
                            controlling.setPitch(0);
                        } catch (RPCException q) {
                            q.printStackTrace();
                        }
                    }
                    return false;
                }
            });
            yRight.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch (View v, MotionEvent e) {
                    if (e.getAction() == MotionEvent.ACTION_DOWN) {
                        try {
                            controlling.setYaw(1);
                        } catch (RPCException q) {
                            q.printStackTrace();
                        }
                    } else if (e.getAction() == MotionEvent.ACTION_UP) {
                        try {
                            controlling.setYaw(0);
                        } catch (RPCException q) {
                            q.printStackTrace();
                        }
                    }
                    return false;
                }
            });
            yLeft.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch (View v, MotionEvent e) {
                    if (e.getAction() == MotionEvent.ACTION_DOWN) {
                        try {
                            controlling.setYaw(-1);
                        } catch (RPCException q) {
                            q.printStackTrace();
                        }
                    } else if (e.getAction() == MotionEvent.ACTION_UP) {
                        try {
                            controlling.setYaw(0);
                        } catch (RPCException q) {
                            q.printStackTrace();
                        }
                    }
                    return false;
                }
            });
            rLeft.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch (View v, MotionEvent e) {
                    if (e.getAction() == MotionEvent.ACTION_DOWN) {
                        try {
                            controlling.setRoll(-1);
                        } catch (RPCException q) {
                            q.printStackTrace();
                        }
                    } else if (e.getAction() == MotionEvent.ACTION_UP) {
                        try {
                            controlling.setRoll(0);
                        } catch (RPCException q) {
                            q.printStackTrace();
                        }
                    }
                    return false;
                }
            });

            rRight.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch (View v, MotionEvent e) {
                    if (e.getAction() == MotionEvent.ACTION_DOWN) {
                        try {
                            controlling.setRoll(1);
                        } catch (RPCException q) {
                            q.printStackTrace();
                        }
                    } else if (e.getAction() == MotionEvent.ACTION_UP) {
                        try {
                            controlling.setRoll(0);
                        } catch (RPCException q) {
                            q.printStackTrace();
                        }
                    }
                    return false;
                }
            });
            sas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    try {
                        if (isChecked) {
                            controlling.setSAS(true);
                            System.out.println("t");
                        } else {
                            controlling.setSAS(false);
                        }
                    } catch (RPCException e) {
                        e.printStackTrace();
                    }
                }
            });
            thrust.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    try {
                        controlling.setThrottle((float) progress / 100);
                        System.out.println(progress);
                    } catch (RPCException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
            // if didnt work
        } catch (IOException e) {
            q.setText("Connection failed");
            q.setVisibility(View.VISIBLE);
        } catch (RPCException e) {
            q.setText("RPC crash");
            q.setVisibility(View.VISIBLE);
        }
    }
}
