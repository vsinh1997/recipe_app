import api from "./api.js";
import {
    API_RECIPE_DELETE_URL,
    API_RECIPE_INSERT_URL,
    API_RECIPE_SELECT_ONE_URL,
    API_RECIPE_SELECT_URL,
    API_RECIPE_UPDATE_URL
} from "../constants/appConst.js";

/**
 * 「レシピ」のAPIを呼び出します。
 */
export const RecipeService = {
    // 一覧取得
    select(cond) {
        return api.get(API_RECIPE_SELECT_URL);
    },
    //１レコード取得
    selectOne(id) {
        return api.get(API_RECIPE_SELECT_ONE_URL.replace("{id}", id));
    },
    // １レコード追加
    insert(data) {
        return api.post(API_RECIPE_INSERT_URL, data);
    },
    // １レコード更新
    update(id, data) {
        return api.put(API_RECIPE_UPDATE_URL.replace("{id}", id), data);
    },
    // １レコード削除
    delete(id) {
        return api.delete(API_RECIPE_DELETE_URL.replace("{id}", id));
    },
}
