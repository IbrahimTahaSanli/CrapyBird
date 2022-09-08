package com.tahasanli.gdxplatformer;

import com.badlogic.gdx.math.Octree;

public enum ComponentCode {
    Comp(0),
    InputComp(1),
    MoveComp(2),
    ColliderComp(3),
    SceneComp(4),
    TextureComp(5),
    UITextComp(6),
    EnemyComp(7)


;
    public final int value;
    ComponentCode(int i) {
        this.value = i;
    }
}
