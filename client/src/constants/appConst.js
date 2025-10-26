// サービス呼出のベースURL
export const API_BASE_URL = 'http://localhost:8080';

//==================================================
// レシピの各URL
//==================================================
// ベースURL
export const API_RECIPE_BASE_URL = API_BASE_URL + '/recipe';
// 一覧取得
export const API_RECIPE_SELECT_URL = API_RECIPE_BASE_URL;
// １レコード取得
export const API_RECIPE_SELECT_ONE_URL = API_RECIPE_BASE_URL.concat("/{id}");
// １レコード追加
export const API_RECIPE_INSERT_URL = API_RECIPE_BASE_URL;
// １レコード更新
export const API_RECIPE_UPDATE_URL = API_RECIPE_BASE_URL.concat("/{id}");
// １レコード削除
export const API_RECIPE_DELETE_URL = API_RECIPE_BASE_URL.concat("/{id}");


