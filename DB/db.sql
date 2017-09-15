-- Creator:       MySQL Workbench 6.3.9/ExportSQLite Plugin 0.1.0
-- Author:        anthony.succu
-- Caption:       New Model
-- Project:       Name of the project
-- Changed:       2017-09-15 13:46
-- Created:       2017-09-15 09:04
PRAGMA foreign_keys = OFF;

-- Schema: player
ATTACH "player.sdb" AS "player";
BEGIN;
CREATE TABLE "player"."stats"(
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
CREATE TABLE "player"."cat_comp"(
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
CREATE TABLE "player"."job"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "limit_compt_princ" INTEGER NOT NULL,
  "limit_compt_second" VARCHAR(45) NOT NULL
);
CREATE TABLE "player"."comp"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "cat_comp" INTEGER NOT NULL,
  "titre" VARCHAR(45) NOT NULL,
  "description" LONGTEXT,
  CONSTRAINT "fk_comp_cat_comp1"
    FOREIGN KEY("cat_comp")
    REFERENCES "cat_comp"("id")
);
CREATE INDEX "player"."comp.fk_comp_cat_comp1_idx" ON "comp" ("cat_comp");
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(DEFAULT, 1, 'attaque', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(DEFAULT, 1, 'parade', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(DEFAULT, 1, 'esquive', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(DEFAULT, 1, 'modules d''armes', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(DEFAULT, 1, 'Arts martiaux', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(DEFAULT, 1, 'port d''armure', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(DEFAULT, 2, 'Talent Runique', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(DEFAULT, 2, 'Projection Runique', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(DEFAULT, 2, 'Modules de projection runique', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(DEFAULT, 3, 'acrobaties', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(DEFAULT, 3, 'athetisme', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(DEFAULT, 3, 'equitation', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(DEFAULT, 3, 'escalade', NULL);
INSERT OR REPLACE INTO "comp"("id","cat_comp","titre","description") VALUES(DEFAULT, 3, 'saut', NULL);
CREATE TABLE "player"."player"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "job" INTEGER NOT NULL,
  "name" VARCHAR(255) NOT NULL,
  "EXP" INTEGER NOT NULL DEFAULT 0,
  "lvl" INTEGER NOT NULL DEFAULT 1,
  "skill_point" INTEGER NOT NULL DEFAULT 600,
  "size" INTEGER,
  "race" VARCHAR(45),
  "origin" VARCHAR(45),
  "sexe" INTEGER DEFAULT 1,
  "weight" INTEGER,
  "age" INTEGER,
  "playercol" VARCHAR(45),
  CONSTRAINT "fk_player_job1"
    FOREIGN KEY("job")
    REFERENCES "job"("id")
);
CREATE INDEX "player"."player.fk_player_job1_idx" ON "player" ("job");
CREATE TABLE "player"."player_has_stats"(
  "player" INTEGER NOT NULL,
  "stats" INTEGER NOT NULL,
  "value" VARCHAR(45) NOT NULL,
  PRIMARY KEY("player","stats"),
  CONSTRAINT "fk_player_has_stats_player1"
    FOREIGN KEY("player")
    REFERENCES "player"("id"),
  CONSTRAINT "fk_player_has_stats_stats1"
    FOREIGN KEY("stats")
    REFERENCES "stats"("id")
);
CREATE INDEX "player"."player_has_stats.fk_player_has_stats_stats1_idx" ON "player_has_stats" ("stats");
CREATE INDEX "player"."player_has_stats.fk_player_has_stats_player1_idx" ON "player_has_stats" ("player");
CREATE TABLE "player"."job_has_comp"(
  "job" INTEGER NOT NULL,
  "comp" INTEGER NOT NULL,
  "cost" INTEGER NOT NULL,
  PRIMARY KEY("job","comp"),
  CONSTRAINT "fk_job_has_comp_job1"
    FOREIGN KEY("job")
    REFERENCES "job"("id"),
  CONSTRAINT "fk_job_has_comp_comp1"
    FOREIGN KEY("comp")
    REFERENCES "comp"("id")
);
CREATE INDEX "player"."job_has_comp.fk_job_has_comp_comp1_idx" ON "job_has_comp" ("comp");
CREATE INDEX "player"."job_has_comp.fk_job_has_comp_job1_idx" ON "job_has_comp" ("job");
CREATE TABLE "player"."comp_has_player"(
  "comp" INTEGER NOT NULL,
  "player" INTEGER NOT NULL,
  "value" INTEGER NOT NULL,
  PRIMARY KEY("comp","player"),
  CONSTRAINT "fk_comp_has_player_comp1"
    FOREIGN KEY("comp")
    REFERENCES "comp"("id"),
  CONSTRAINT "fk_comp_has_player_player1"
    FOREIGN KEY("player")
    REFERENCES "player"("id")
);
CREATE INDEX "player"."comp_has_player.fk_comp_has_player_player1_idx" ON "comp_has_player" ("player");
CREATE INDEX "player"."comp_has_player.fk_comp_has_player_comp1_idx" ON "comp_has_player" ("comp");
COMMIT;
