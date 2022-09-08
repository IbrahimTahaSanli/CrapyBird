package com.tahasanli.gdxplatformer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Component {
    public static final String Name = "Component";
    public GameObject parent = null;
    public int Code = 0;
    public boolean Enabled = true;


    public abstract void onCreate();

    public abstract void onFrame(SpriteBatch batch);
}
