DROP TABLE IF EXISTS ingredient;

CREATE TABLE IF NOT EXISTS ingredient
(
    id        SERIAL PRIMARY KEY,
    recipe_id INT REFERENCES recipe (id),
    name      VARCHAR(255),
    amount    VARCHAR(100)
);

COMMENT ON TABLE ingredient IS '材料テーブル';
COMMENT ON COLUMN ingredient.id IS '材料番号';
COMMENT ON COLUMN ingredient.recipe_id IS 'レシピ番号';
COMMENT ON COLUMN ingredient.name IS '材料名称';
COMMENT ON COLUMN ingredient.amount IS '数量';
