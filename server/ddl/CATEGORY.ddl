DROP TABLE IF EXISTS category;

CREATE TABLE IF NOT EXISTS category
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

COMMENT ON TABLE category IS 'カテゴリテーブル';
COMMENT ON COLUMN category.id IS 'カテゴリ番号';
COMMENT ON COLUMN category.name IS 'カテゴリ名称';