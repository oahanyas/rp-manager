-- Creator:       MySQL Workbench 6.3.9/ExportSQLite Plugin 0.1.0
-- Author:        anthony.succu
-- Caption:       New Model
-- Project:       Name of the project
-- Changed:       2017-12-18 12:08
-- Created:       2017-09-15 09:04
PRAGMA foreign_keys = OFF;

-- Schema: player
BEGIN;
CREATE TABLE "stats"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "type" VARCHAR(45) NOT NULL,
  "description" LONGTEXT NOT NULL
);
INSERT OR REPLACE INTO "stats"("id","type","description") VALUES(1, 'Force', 'La Force évalue la capacité d’accomplir des efforts physiques. la musculature et la vigueur du personnage. Cette caractéristique permet de  soulever des poids Importants, d''infliger plus de dégâts à chaque coup et de briser des objets');
INSERT OR REPLACE INTO "stats"("id","type","description") VALUES(2, 'Dexterite', 'La Dextérité détermine l''habileté manuelle du personnage. la rapidité de ses gestes et sa précision. Avec l''Agilité, elle représente sa vitesse de réaction. c''est à dire sa capacité à répondre aussi rapidement que possible à une situation dangereuse. une menace ou un problème.');
INSERT OR REPLACE INTO "stats"("id","type","description") VALUES(3, 'Agilite', 'L‘Agilité représente l''habileté corporelle du personnage. sa vitesse de déplacement et son sens de l’équlllbre. Elle mesure son aptitude acrobatique ou sa facilité à esquiver les attaques. Avec la Dextérlté. cette caractéristique influence sa vitesse de réaction.');
INSERT OR REPLACE INTO "stats"("id","type","description") VALUES(4, 'Constitution', 'La Constitution mesure l''endurance et la vitalité du personnage. sa résistance à la fatigue et sa capacité à encaisser les coups. Un personnage avec une Constitution élevée a une grande résistance physique et récupère bien des maladies et des blessures. Cette caractéristique indique la quantité de dégâts que peut subir le personnage avant de mourir.');
INSERT OR REPLACE INTO "stats"("id","type","description") VALUES(5, 'Intelligence', 'L‘Intelligence concerne les champs du raisonnement. de l''apprentissage et de la mémoire. De cette caractéristique dépendent les connaissances intellectuelles et logiques. ainsi que la capacité du personnage à comprendre et développer les sorts.');
INSERT OR REPLACE INTO "stats"("id","type","description") VALUES(6, 'Pouvoir', 'Cette caractéristique représente la puissance animique ou spirituelle du personnage. Plus elle est élevée, plus il pourra inﬂuencer ou résister ausurnaturel et se démarquer des autres individus.');
INSERT OR REPLACE INTO "stats"("id","type","description") VALUES(7, 'Perception', 'Cette caractéristique mesure Faculté sensorielle du personnage et sa capacité à remarquer ce qui se passe autour de lui. Elle représente ses cinq sens et son instinct.');
INSERT OR REPLACE INTO "stats"("id","type","description") VALUES(8, 'Volonte', 'La Volonté mesure la vigueur mentale d''un personnage, son impassibilité et sa ténacité. On l‘utilise aussi bien pour résister aux disciplines psychiques que pour les employer.');
CREATE TABLE "cat_comp"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "titre" VARCHAR(45) NOT NULL,
  "principal" INTEGER NOT NULL DEFAULT 0
);
INSERT OR REPLACE INTO "cat_comp"("id","titre","principal") VALUES(1, 'matial art', 1);
INSERT OR REPLACE INTO "cat_comp"("id","titre","principal") VALUES(2, 'runique art', 1);
INSERT OR REPLACE INTO "cat_comp"("id","titre","principal") VALUES(3, 'athletiques', 0);
INSERT OR REPLACE INTO "cat_comp"("id","titre","principal") VALUES(4, 'socials', 0);
INSERT OR REPLACE INTO "cat_comp"("id","titre","principal") VALUES(5, 'vitales', 0);
INSERT OR REPLACE INTO "cat_comp"("id","titre","principal") VALUES(6, 'creatives', 0);
INSERT OR REPLACE INTO "cat_comp"("id","titre","principal") VALUES(7, 'sensorielles', 0);
INSERT OR REPLACE INTO "cat_comp"("id","titre","principal") VALUES(8, 'intellectuelles', 0);
INSERT OR REPLACE INTO "cat_comp"("id","titre","principal") VALUES(9, 'clandestines', 0);
CREATE TABLE "type_avantage"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "type" VARCHAR(45) NOT NULL
);
CREATE TABLE "job"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "titre" VARCHAR(45) NOT NULL,
  "description" LONGTEXT NOT NULL,
  "achetype" VARCHAR(45) NOT NULL,
  "multiplicateur_pv" INTEGER NOT NULL,
  "pv" INTEGER NOT NULL,
  "initiative" INTEGER NOT NULL,
  "rune_inne" VARCHAR(45) NOT NULL
);
INSERT OR REPLACE INTO "job"("id","titre","description","achetype","multiplicateur_pv","pv","initiative","rune_inne") VALUES(1, 'guerrier', 'Le guerrier incarne l’essence de l''archétype du combattant. Cette classe englobe tous les individus qui ont dédié leur vie entière au combat et qui sont capables d’exploiter au maximum leurs capacités guerrières. Cela les conduit à maîtriser non seulement les armes mais aussi, quand le moment est venu, à utiliser leur énergie animique au combat. lis développent aussi avec facilité de grandes connaissances dans le domaine des tactiques militaires et savent diriger des armées. Traditionnellement, les guerriers peuvent remplir de nombreux rôles, depuis celui de simple mercenaire à celui de chevalier.', 'combattant', 15, 15, 5, '1/3');
INSERT OR REPLACE INTO "job"("id","titre","description","achetype","multiplicateur_pv","pv","initiative","rune_inne") VALUES(2, 'guerrier acrobate', 'Les guerriers acrobates sont des combattants spécialisés dans l‘optimisation de leur agilité et de leur rapidité. Leur avantage principal consiste à anticiper les mouvements de leurs adversaires et a essayer d ’en ﬁnir avec eux avant qu’ils ne réagissent. ils préfèrent aussi esquiver les attaques et rester aussi loin que possible de l ''endroit où porte un coup. ils bénéﬁcient d''une grande agilité, s’avèrent capables de sauter, de tomber au de courir avec une habileté que peu parviennent à égaler. ils peuvent jouer toutes sortes de rôles dans le monde, mais assurent généralement des', 'combattant', 20, 10, 10, '1/3');
CREATE TABLE "job_has_cat_comp"(
  "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "job" INTEGER NOT NULL,
  "cat_comp" INTEGER NOT NULL,
  "limit" INTEGER NOT NULL DEFAULT 100,
  "default_cout" INTEGER NOT NULL,
  CONSTRAINT "fk_job_has_cat_comp_job1"
    FOREIGN KEY("job")
    REFERENCES "job"("id"),
  CONSTRAINT "fk_job_has_cat_comp_cat_comp1"
    FOREIGN KEY("cat_comp")
    REFERENCES "cat_comp"("id")
);
CREATE INDEX "job_has_cat_comp.fk_job_has_cat_comp_cat_comp1_idx" ON "job_has_cat_comp" ("cat_comp");
CREATE INDEX "job_has_cat_comp.fk_job_has_cat_comp_job1_idx" ON "job_has_cat_comp" ("job");
INSERT OR REPLACE INTO "job_has_cat_comp"("id","job","cat_comp","limit","default_cout") VALUES(DEFAULT, 1, 1, 60, 2);
INSERT OR REPLACE INTO "job_has_cat_comp"("id","job","cat_comp","limit","default_cout") VALUES(DEFAULT, 1, 2, 50, 3);
INSERT OR REPLACE INTO "job_has_cat_comp"("id","job","cat_comp","limit","default_cout") VALUES(DEFAULT, 1, 3, 0, 2);
INSERT OR REPLACE INTO "job_has_cat_comp"("id","job","cat_comp","limit","default_cout") VALUES(DEFAULT, 1, 4, 0, 2);
INSERT OR REPLACE INTO "job_has_cat_comp"("id","job","cat_comp","limit","default_cout") VALUES(DEFAULT, 1, 5, 0, 2);
INSERT OR REPLACE INTO "job_has_cat_comp"("id","job","cat_comp","limit","default_cout") VALUES(DEFAULT, 1, 6, 0, 2);
INSERT OR REPLACE INTO "job_has_cat_comp"("id","job","cat_comp","limit","default_cout") VALUES(DEFAULT, 1, 7, 0, 2);
INSERT OR REPLACE INTO "job_has_cat_comp"("id","job","cat_comp","limit","default_cout") VALUES(DEFAULT, 1, 8, 0, 3);
INSERT OR REPLACE INTO "job_has_cat_comp"("id","job","cat_comp","limit","default_cout") VALUES(DEFAULT, 1, 9, 0, 2);
CREATE TABLE "advantage"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "type" INTEGER,
  "effect" LONGTEXT NOT NULL,
  "condition" LONGTEXT NOT NULL,
  "cout" INTEGER NOT NULL,
  "limit" INTEGER NOT NULL DEFAULT 1,
  CONSTRAINT "fk_advantage_type_avantage1"
    FOREIGN KEY("type")
    REFERENCES "type_avantage"("id")
);
CREATE INDEX "advantage.fk_advantage_type_avantage1_idx" ON "advantage" ("type");
CREATE TABLE "comp"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "cat_comp" INTEGER NOT NULL,
  "titre" VARCHAR(45) NOT NULL,
  "description" LONGTEXT,
  CONSTRAINT "fk_comp_cat_comp1"
    FOREIGN KEY("cat_comp")
    REFERENCES "cat_comp"("id")
);
CREATE INDEX "comp.fk_comp_cat_comp1_idx" ON "comp" ("cat_comp");
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(1, 1, 'attaque', 'Compétence mesurant l’habileté du personnage à attaquer son adversaire. à passer sa garde et à lui inﬂiger des dégâts. Dépend dela Dextérîté.');
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(2, 1, 'parade', 'Compétence de défense permettant de bloquer les attaques. Dépend de la Dextérité.');
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(3, 1, 'esquive', 'Compétence de défense permettant de se mettre hors de la tralectoire d''une attaque. Dépend de l''Agilité.');
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(4, 1, 'modules d''armes', 'Capacités liées au maniement des armes.');
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(5, 1, 'Arts martiaux', 'Capacités martiales de combat à mains nues.');
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(6, 1, 'port d''armure', 'Capacité mesurant l''aisance en armure. Permet d''éviter les malus et les restrictions liés aux armures. Se calcule comme une compétence et dépend de la Force.');
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(7, 2, 'Talent Runique', 'Ces points permettent entre autres de développer des pouvoirs et d’augmenter leur puissance.');
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(8, 2, 'Projection Runique', 'Compétence servant à projeter correctement ses pouvoirs. Dépend dela Dextérité.');
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(9, 2, 'Modules de projection runique', 'Capacite permettant d''utiliser ses compétences martiales lorsque l’on projette ses pouvoirs sur une cible.');
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(10, 3, 'Acrobaties', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(11, 3, 'Athetisme', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(12, 3, 'Equitation', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(13, 3, 'Escalade', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(14, 3, 'Saut', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(15, 4, 'Commandement', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(16, 4, 'Intimidation', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(17, 4, 'Persuasion', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(18, 4, 'Style', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(19, 5, 'Impassibilite', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(20, 5, 'Prouesses de force', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(21, 5, 'Resistance à la douleur', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(22, 6, 'Art', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(23, 6, 'Danse', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(24, 6, 'Forge', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(25, 6, 'Habilite manuelle', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(26, 6, 'Musique', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(27, 7, 'Observation', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(28, 7, 'Pistage', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(29, 7, 'Vigilance', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(30, 8, 'Animaux', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(31, 8, 'Estimation', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(32, 8, 'Evaluation runique', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(33, 8, 'Herboriste', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(34, 8, 'Histoire', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(35, 8, 'Medecine', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(36, 8, 'Memorisation', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(37, 8, 'Navigation', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(38, 8, 'Occultisme', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(39, 8, 'Science', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(40, 9, 'Camouflage', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(41, 9, 'Crochetage', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(42, 9, 'Deguisement', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(43, 9, 'Discetion', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(44, 9, 'Larcin', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(45, 9, 'Piege', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(46, 9, 'Poisons', NULL);
CREATE TABLE "player"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "job" INTEGER,
  "name" VARCHAR(255) NOT NULL,
  "exp" INTEGER NOT NULL DEFAULT 0,
  "lvl" INTEGER NOT NULL DEFAULT 1,
  "skill_point" INTEGER NOT NULL DEFAULT 600,
  "size" INTEGER,
  "race" VARCHAR(45),
  "origin" VARCHAR(45),
  "sexe" INTEGER DEFAULT 1,
  "weight" INTEGER,
  "age" INTEGER,
  CONSTRAINT "fk_player_job1"
    FOREIGN KEY("job")
    REFERENCES "job"("id")
);
CREATE INDEX "player.fk_player_job1_idx" ON "player" ("job");
CREATE TABLE "player_has_stats"(
  "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "player" INTEGER NOT NULL,
  "stats" INTEGER NOT NULL,
  "value" VARCHAR(45) NOT NULL,
  CONSTRAINT "fk_player_has_stats_player1"
    FOREIGN KEY("player")
    REFERENCES "player"("id"),
  CONSTRAINT "fk_player_has_stats_stats1"
    FOREIGN KEY("stats")
    REFERENCES "stats"("id")
);
CREATE INDEX "player_has_stats.fk_player_has_stats_stats1_idx" ON "player_has_stats" ("stats");
CREATE INDEX "player_has_stats.fk_player_has_stats_player1_idx" ON "player_has_stats" ("player");
CREATE TABLE "job_has_comp"(
  "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "job" INTEGER NOT NULL,
  "comp" INTEGER NOT NULL,
  "value" INTEGER NOT NULL DEFAULT 1,
  "bonus" INTEGER NOT NULL DEFAULT 0,
  "bonus_max" INTEGER NOT NULL,
  CONSTRAINT "fk_job_has_comp_job1"
    FOREIGN KEY("job")
    REFERENCES "job"("id"),
  CONSTRAINT "fk_job_has_comp_comp1"
    FOREIGN KEY("comp")
    REFERENCES "comp"("id")
);
CREATE INDEX "job_has_comp.fk_job_has_comp_comp1_idx" ON "job_has_comp" ("comp");
CREATE INDEX "job_has_comp.fk_job_has_comp_job1_idx" ON "job_has_comp" ("job");
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 1, 1, 2, 5, 50);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 1, 2, 2, 5, 50);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 1, 6, 2, 5, 0);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 1, 20, 1, 5, 0);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 1, 8, 3, 0, 0);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 1, 9, 20, 0, 0);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 2, 1, 2, 5, 50);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 2, 2, 3, 0, 0);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 2, 3, 2, 5, 50);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 2, 6, 2, 0, 0);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 2, 10, 2, 10, 0);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 2, 14, 2, 10, 0);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 2, 11, 2, 10, 0);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 2, 25, 2, 10, 0);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 2, 18, 2, 10, 0);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 2, 8, 3, 0, 0);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 2, 9, 20, 0, 0);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 3, 6, 1, 10, 0);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 3, 1, 2, 5, 50);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 3, 2, 2, 5, 50);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 3, 3, 2, 0, 0);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 3, 6, 1, 10, 0);
INSERT OR REPLACE INTO "job_has_comp"("id","job","comp","value","bonus","bonus_max") VALUES(DEFAULT, 3, 20, 1, 5, 0);
CREATE TABLE "comp_has_player"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "comp" INTEGER NOT NULL,
  "player" INTEGER NOT NULL,
  "value" INTEGER NOT NULL,
  CONSTRAINT "fk_comp_has_player_comp1"
    FOREIGN KEY("comp")
    REFERENCES "comp"("id"),
  CONSTRAINT "fk_comp_has_player_player1"
    FOREIGN KEY("player")
    REFERENCES "player"("id")
);
CREATE INDEX "comp_has_player.fk_comp_has_player_player1_idx" ON "comp_has_player" ("player");
CREATE INDEX "comp_has_player.fk_comp_has_player_comp1_idx" ON "comp_has_player" ("comp");
CREATE TABLE "player_has_advantage"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "player" INTEGER NOT NULL,
  "advantage" INTEGER NOT NULL,
  CONSTRAINT "fk_player_has_advantage_player1"
    FOREIGN KEY("player")
    REFERENCES "player"("id"),
  CONSTRAINT "fk_player_has_advantage_advantage1"
    FOREIGN KEY("advantage")
    REFERENCES "advantage"("id")
);
CREATE INDEX "player_has_advantage.fk_player_has_advantage_advantage1_idx" ON "player_has_advantage" ("advantage");
CREATE INDEX "player_has_advantage.fk_player_has_advantage_player1_idx" ON "player_has_advantage" ("player");
COMMIT;
