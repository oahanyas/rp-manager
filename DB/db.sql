-- Creator:       MySQL Workbench 6.3.9/ExportSQLite Plugin 0.1.0
-- Author:        anthony.succu
-- Caption:       New Model
-- Project:       Name of the project
-- Changed:       2017-09-15 11:56
-- Created:       2017-09-15 09:04
PRAGMA foreign_keys = OFF;

-- Schema: player
ATTACH "player.sdb" AS "player";
BEGIN;
CREATE TABLE "player"."player"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "name" VARCHAR(255) NOT NULL,
  "EXP" INTEGER NOT NULL DEFAULT 0,
  "lvl" INTEGER NOT NULL DEFAULT 1,
  "skill_point" INTEGER NOT NULL DEFAULT 600,
  "size" INTEGER,
  "race" VARCHAR(45),
  "origin" VARCHAR(45),
  "sexe" INTEGER DEFAULT 1,
  "weight" INTEGER
);
CREATE TABLE "player"."stats"(
  "type" VARCHAR(45) PRIMARY KEY NOT NULL,
  "description" LONGTEXT NOT NULL
);
INSERT INTO "stats"("type","description") VALUES('Force', 'La Force évalue la capacité d’accomplir des efforts physiques. la musculature et la vigueur du personnage. Cette caractéristique permet de  soulever des poids Importants, d''infliger plus de dégâts à chaque coup et de briser des objets');
INSERT INTO "stats"("type","description") VALUES('Dexterite', 'La Dextérité détermine l''habileté manuelle du personnage. la rapidité de ses gestes et sa précision. Avec l''Agilité, elle représente sa vitesse de réaction. c''est à dire sa capacité à répondre aussi rapidement que possible à une situation dangereuse. une menace ou un problème.');
INSERT INTO "stats"("type","description") VALUES('Agilite', 'L‘Agilité représente l''habileté corporelle du personnage. sa vitesse de déplacement et son sens de l’équlllbre. Elle mesure son aptitude acrobatique ou sa facilité à esquiver les attaques. Avec la Dextérlté. cette caractéristique influence sa vitesse de réaction.');
INSERT INTO "stats"("type","description") VALUES('Constitution', 'La Constitution mesure l''endurance et la vitalité du personnage. sa résistance à la fatigue et sa capacité à encaisser les coups. Un personnage avec une Constitution élevée a une grande résistance physique et récupère bien des maladies et des blessures. Cette caractéristique indique la quantité de dégâts que peut subir le personnage avant de mourir.');
INSERT INTO "stats"("type","description") VALUES('Intelligence', 'L‘Intelligence concerne les champs du raisonnement. de l''apprentissage et de la mémoire. De cette caractéristique dépendent les connaissances intellectuelles et logiques. ainsi que la capacité du personnage à comprendre et développer les sorts.');
INSERT INTO "stats"("type","description") VALUES('Pouvoir', 'Cette caractéristique représente la puissance animique ou spirituelle du personnage. Plus elle est élevée, plus il pourra inﬂuencer ou résister ausurnaturel et se démarquer des autres individus.');
INSERT INTO "stats"("type","description") VALUES('Perception', 'Cette caractéristique mesure Faculté sensorielle du personnage et sa capacité à remarquer ce qui se passe autour de lui. Elle représente ses cinq sens et son instinct.');
INSERT INTO "stats"("type","description") VALUES('Volonte', 'La Volonté mesure la vigueur mentale d''un personnage, son impassibilité et sa ténacité. On l‘utilise aussi bien pour résister aux disciplines psychiques que pour les employer.');
CREATE TABLE "player"."talent"(
  "id" INTEGER PRIMARY KEY NOT NULL
);
CREATE TABLE "player"."compt"(
  "id" INTEGER PRIMARY KEY NOT NULL
);
CREATE TABLE "player"."player_has_stats"(
  "player" INTEGER NOT NULL,
  "stats" VARCHAR(45) NOT NULL,
  "value" INTEGER NOT NULL,
  PRIMARY KEY("player","stats"),
  CONSTRAINT "fk_player_has_stats_player1"
    FOREIGN KEY("player")
    REFERENCES "player"("id"),
  CONSTRAINT "fk_player_has_stats_stats1"
    FOREIGN KEY("stats")
    REFERENCES "stats"("type")
);
CREATE INDEX "player"."player_has_stats.fk_player_has_stats_stats1_idx" ON "player_has_stats" ("stats");
CREATE INDEX "player"."player_has_stats.fk_player_has_stats_player1_idx" ON "player_has_stats" ("player");
COMMIT;
