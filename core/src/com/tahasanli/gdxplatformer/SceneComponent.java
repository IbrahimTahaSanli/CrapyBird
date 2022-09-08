package com.tahasanli.gdxplatformer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SceneComponent extends  Component{
    public static final String Name = "SceneComponent";
    public int Code = 4;

    private float MinLimit, MaxFloat;

    public SceneComponent(float minLimit, float maxLimit){
        super();
        super.Code = Code;

        this.MinLimit = minLimit;
        this.MaxFloat = maxLimit;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onFrame(SpriteBatch batch) {
        if(this.parent.Position.y < this.MinLimit || this.parent.Position.y > this.MaxFloat)
            ((Character)this.parent).OutsideOfScreen();
    }
}
