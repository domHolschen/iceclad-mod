package IcecladMod.actions;

import IcecladMod.cardmodifiers.BriskCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.ArrayList;

public class NilAction extends AbstractGameAction {

    public NilAction() {
    }
    
    @Override
    public void update() {
        for (AbstractCard c : DrawCardAction.drawnCards) {
            if (c.costForTurn == 0 || c.freeToPlayOnce) {
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
            }
        }
        isDone = true;
    }
}
