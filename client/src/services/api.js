import axios from "axios";

// 共通ベースのURL
const api = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 10000 // 10秒
});

// ==================================================
//  インターセッター追加
// ==================================================
// リクエスト
api.interceptors.request.use(config => {
    return config;
});

// レスポンス
api.interceptors.response.use(
    response => response.data,
    error => {
    console.error("サーバー接続に失敗しました。");
    return Promise.reject(error);
});

export default api;