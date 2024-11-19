import java.util.List;

import static java.lang.Math.abs;

public class ValorBattle {
    private final LegendsOfValor game;
    BattleManager battleManager;

    private List<Monster> monsters;
    List<int[]> monstersPositions;
    private final int[][] nexusPositions = {{0, 0}, {0, 3}, {0, 6}};

    int round = 0;
    int roundsForNewMonsters = 5;

    ValorBattle(LegendsOfValor game) {

        this.game = game;
        battleManager = new BattleManager(game.getHeroes(), monsters);
    }

    List<int[]> getMonstersPositions() {
        return monstersPositions;
    }

    boolean withinAttackRange(int heroIdx, int monsterIdx){
        // Whether an enemy is within range to attack.
        return abs(game.playerPositions[heroIdx][0] - monstersPositions.get(monsterIdx)[0]) < 1 && abs(game.playerPositions[heroIdx][1] - monstersPositions.get(monsterIdx)[1]) < 1;
    }


    void heroMove(Hero hero, Monster monster, int action){
        ActionStrategy actionStrategy;
        switch (action){
            case 1:
                actionStrategy = new AttackStrategy();
                break;
            case 2:
                actionStrategy = new CastSpellStrategy();
                break;
            case 3:
                actionStrategy = new UsePotionStrategy();
                break;
            default:
                System.out.println("Invalid action Type");
                return;
        }
        actionStrategy.execute(hero, monster);
        // If monster is dead: remove it from the list
    }


    void round(){
        if(round % roundsForNewMonsters == 0){
            createNewMonsters();
        }
        heroesMove();
        monstersMove();
        round++;
    }

    void heroesMove(){
        // prompt, call game.move/purchase & heroMove
        for(int heroIdx = 0; heroIdx < game.getHeroes().size(); heroIdx++){
            while(!game.getHeroAction(heroIdx)) /* Enter Again */;
        }
    }

    void monstersMove(){
        // Move or attack heroes by chance
        for(int monsterIdx = 0; monsterIdx < monsters.size(); monsterIdx++){
            int heroIdx = monstersPositions.get(monsterIdx)[1]/2;
            if(withinAttackRange(heroIdx, monsterIdx)){
                monsterMove(game.getHeroes().get(heroIdx), monsters.get(monsterIdx));
            }else{
                monsterPosMove(monsterIdx);
            }

        }

    }

    void monsterMove(Hero hero, Monster monster){
        if (monster.getHP() > 0) {
            battleManager.performMonsterAction(monster, hero);
        }
        // If hero is dead: initialize its position & reset MP
    }

    void monsterPosMove(int monsterIdx){
        monstersPositions.get(monsterIdx)[1]++;
    }

    void createNewMonsters(){
        for (int i = 0; i < nexusPositions.length; i++) {
            Hero hero = game.getHeroes().get(i);
            monsters.add(MonsterFactory.getRandomMonster(hero.getExperienceLevel()));
            monstersPositions.add(nexusPositions[i]);
        }
    }
}
