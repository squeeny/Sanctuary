package Sanctuary.vfx.general;

import Sanctuary.Sanctuary;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;


public class MenacingVFX extends AbstractGameEffect {

    private static final float DUR = 1.0F;
    private float x;
    private float y;
    private float vY;
    private float floor;
    private static final float GRAVITY;

    private static TextureRegion img = null;
    public String link;
    public Texture imag;

    public MenacingVFX(float x, float y, float maxHeight) {

        if (img == null) {
            String link= Sanctuary.makeEffectPath("jojo.png");
            imag = new Texture(link);
            img = new TextureRegion(imag);

        }

        this.duration = MathUtils.random(0.5F, DUR);
        this.x = x - (float) (this.img.getRegionWidth() / 2);
        this.y = y - (float) (this.img.getRegionHeight() / 2);

        this.color = Color.PURPLE;

        this.color.a = 1.0F;
        this.rotation = MathUtils.random(-10.0F, 10.0F);
        this.scale = MathUtils.random(2.0F, 4.0F) * Settings.scale;
        this.vY = MathUtils.random(0f, maxHeight) * Settings.scale;
        this.floor = MathUtils.random(100.0F, 250.0F) * Settings.scale;
    }

    public void update() {
        this.vY += GRAVITY / this.scale * Gdx.graphics.getDeltaTime();
        this.y += this.vY * Gdx.graphics.getDeltaTime();
        if (this.scale > 0.3F * Settings.scale) {
            this.scale -= Gdx.graphics.getDeltaTime() * 2.0F;
        }

        if (this.y < this.floor) {
            this.vY = -this.vY * 0.75F;
            this.y = this.floor + 0.1F;
        }

        if (1.0F - this.duration < 0.1F) {
            //this.color.a = Interpolation.fade.apply(0.0F, 1.0F, (DUR - this.duration) * 10.0F);
        } else {
            //this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, (float) this.img.getRegionWidth() / 2.0F, (float) this.img.getRegionHeight() / 2.0F, (float) this.img.getRegionWidth(), (float) this.img.getRegionHeight(), this.scale, this.scale, this.rotation);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }

    static {
        GRAVITY = 180.0F * Settings.scale;
    }
}
