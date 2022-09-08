package com.tahasanli.gdxplatformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InputComponent extends Component{
    public static final String Name = "InputComponent";
    public int Code = 1;
    public InputEvent event;

    public InputComponent(InputEvent event){
        super();
        super.Code = Code;

        this.event = event;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onFrame(SpriteBatch batch) {
        if(Gdx.input.isTouched())
            this.event.OnClick(this.parent);
    }
}
