package IcecladMod.actions;

import IcecladMod.cards.Claim;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PlayOtherClaimsAction extends AbstractGameAction {
    public PlayOtherClaimsAction() {}
    
    @Override
    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cardID.equals(new Claim().cardID)) {
                c.freeToPlayOnce = true;
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(c, AbstractDungeon.getMonsters().getRandomMonster(true)));
            }
        }
        this.isDone = true;
    }
}
