package IcecladMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class CountPairsInHandAction extends AbstractGameAction {
    Consumer<Integer> nextAction;

    public CountPairsInHandAction(Consumer<Integer> nextAction) {
        this.nextAction = nextAction;
    }
    
    @Override
    public void update() {
        nextAction.accept(countPairs());
        isDone = true;
    }

    public int countPairs() {
        int pairs = 0;
        List<AbstractCard> cards = new ArrayList<>(AbstractDungeon.player.hand.group);
        while (cards.size() > 1) {
            AbstractCard firstCard = cards.get(0);
            List<AbstractCard> cardsOfOneName = cards.stream().filter(x -> x.cardID.equals(firstCard.cardID)).collect(Collectors.toList());
            pairs += cardsOfOneName.size() / 2;
            for (AbstractCard c : cardsOfOneName) {
                cards.remove(c);
            }
        }
        return pairs;
    }
}
