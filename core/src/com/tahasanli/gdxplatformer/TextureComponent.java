package com.tahasanli.gdxplatformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.tahasanli.gdxplatformer.ComponentCode;

public class TextureComponent extends Component {
    public Texture texture;
    public static final String Name = "TextureComponent";
    public int Code = 5;

    private float AspectRatio;

    public TextureComponent(String texturePath){
        super();

        super.Code = Code;
        this.texture = new Texture(texturePath);

        this.AspectRatio = texture.getWidth() / texture.getHeight();
    }

    public boolean isFixedSize = false;
    public Vector2 FixedSize;

    public void SetFixedSize(boolean isOpen){
        if(isOpen && FixedSize == null)
            FixedSize = new Vector2();
        isFixedSize = isOpen;
    }

    public void SetFixedSize(boolean isOpen, Vector2 size){
        isFixedSize = isOpen;
        FixedSize = size;
    }

    public void SetFixedSize(boolean isOpen, Vector2 size, boolean AspectRatio, boolean AspectWidth){
        isFixedSize = isOpen;
        if(AspectRatio) {
            if (AspectWidth)
                size.set(size.x, size.x * this.AspectRatio);
            else
                size.set(size.y * this.AspectRatio, size.y);
        }
        FixedSize = size;
    }

    public void SetFixedSize(Vector2 size){
        FixedSize = size;
    }

    public void SetFixedSize(Vector2 size, boolean AspectRatio, boolean AspectWidth){
        if(AspectRatio) {
            if (AspectWidth)
                size.set(size.x, size.x * this.AspectRatio);
            else
                size.set(size.y * this.AspectRatio, size.y);
        }
        FixedSize = size;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onFrame(SpriteBatch batch) {
        if(isFixedSize)
            batch.draw(
                    this.texture,
                    this.parent.Position.x - (this.parent.PivotPoint.x * FixedSize.x * this.parent.Scale.x),
                    this.parent.Position.y - (this.parent.PivotPoint.y * FixedSize.y * this.parent.Scale.y),
                    FixedSize.x,
                    FixedSize.y
            );
        else
            batch.draw(
                this.texture,
                this.parent.Position.x - (this.parent.PivotPoint.x * this.texture.getWidth() * this.parent.Scale.x),
                this.parent.Position.y - (this.parent.PivotPoint.y * this.texture.getWidth() * this.parent.Scale.y),
                this.texture.getWidth() * this.parent.Scale.x,
                this.texture.getHeight() * this.parent.Scale.y
                );
    }
}
