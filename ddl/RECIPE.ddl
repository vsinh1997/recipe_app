DROP TABLE IF EXISTS recipe;

CREATE TABLE IF NOT EXISTS recipe (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    cooking_time VARCHAR(50),
    difficulty VARCHAR(20),
    category_id INT REFERENCES category(id)
);

COMMENT ON TABLE	recipe					IS	'レシピテーブル';
COMMENT ON COLUMN	recipe.id				IS	'レシピ番号';
COMMENT ON COLUMN	recipe.name				IS	'レシピ名称';
COMMENT ON COLUMN	recipe.description		IS	'説明';
COMMENT ON COLUMN	recipe.cooking_time		IS	'調理時間';
COMMENT ON COLUMN	recipe.difficulty		IS	'レベル';
COMMENT ON COLUMN	recipe.category_id		IS	'カテゴリ番号';