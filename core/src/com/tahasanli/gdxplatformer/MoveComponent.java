package com.tahasanli.gdxplatformer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MoveComponent extends Component{
    public static final String Name = "MoveComponent";
    public int Code = 2;

    public float Velocity = 0;

    public final float Gravity = 0.4f;

    public MoveComponent(){
        super();
        super.Code = Code;
    }


    @Override
    public void onCreate() {
        super.Code = Code;
    }



    @Override
    public void onFrame(SpriteBatch batch) {
        Velocity -= Gravity;
        this.parent.Position.y += Velocity;
    }

    public void Jump() {
        Velocity = 10;
    }
}
