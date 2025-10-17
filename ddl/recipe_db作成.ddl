CREATE USER recipe_user WITH PASSWORD 'recipe_user';			
CREATE SCHEMA recipe_db;			
ALTER ROLE recipe_user SET search_path to "recipe_db",public;			
GRANT USAGE ON SCHEMA recipe_db TO recipe_user;			
GRANT CREATE ON SCHEMA recipe_db TO recipe_user;