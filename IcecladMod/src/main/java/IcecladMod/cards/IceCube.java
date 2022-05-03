package IcecladMod.cards;

import IcecladMod.IcecladMod;
import IcecladMod.characters.IcecladCharacter;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static IcecladMod.IcecladMod.makeCardPath;

public class IceCube extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = IcecladMod.makeID(IceCube.class.getSimpleName());
    public static final String IMG = makeCardPath("IceCubePlaceholder.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 0;
    private static final int BLOCK = 3;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int DRAW = 1;


    // /STAT DECLARATION/


    public IceCube() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        baseMagicNumber = DRAW;
        magicNumber = baseMagicNumber;
        this.exhaust = false;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (dontTriggerOnUseCard) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        } else {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction((AbstractCreature)p, this.magicNumber));
            this.exhaust = true;
        }
    }

    public void triggerOnEndOfTurnForPlayingCard() {
        this.dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this,true));;
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
