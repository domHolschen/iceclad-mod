package IcecladMod.relics;

import IcecladMod.IcecladMod;
import IcecladMod.powers.IciclePower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import IcecladMod.util.TextureLoader;
import com.sun.java.swing.action.ActionManager;

import java.util.stream.Collectors;

import static IcecladMod.IcecladMod.makeRelicOutlinePath;
import static IcecladMod.IcecladMod.makeRelicPath;

public class FrozenHeart extends CustomRelic {

    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     *
     * Gain 1 energy.
     */

    // ID, images, text.
    public static final String ID = IcecladMod.makeID("FrozenHeart");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    private boolean willApply = false;

    public FrozenHeart() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
        this.pulse = false;
        this.willApply = false;
    }

    @Override
    public void atPreBattle() {
        flash();
        willApply = true;
        beginPulse();
    }

    @Override
    public void atTurnStart() {
        beginPulse();
        this.willApply = true;
        this.pulse = true;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (willApply) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, AbstractDungeon.player, new IciclePower(target, AbstractDungeon.player, damageAmount)));
            this.willApply = false;
            this.pulse = false;
        }
    }

    public void onVictory() {
        this.pulse = false;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
