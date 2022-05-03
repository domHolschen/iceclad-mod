package IcecladMod.actions;

import IcecladMod.cardmodifiers.BriskCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class HighVelocityAction extends AbstractGameAction {

    public HighVelocityAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
    }
    
    @Override
    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (CardModifierManager.hasModifier(c, new BriskCardModifier().identifier(c))) {
                c.freeToPlayOnce = true;
            }
        }
        this.isDone = true;
    }
}
