package flustix.fluxifyed.database.api.routes.modules.xp.leaderboard;

import com.sun.net.httpserver.HttpExchange;
import flustix.fluxifyed.database.api.utils.QueryUtils;
import flustix.fluxifyed.database.api.components.xp.GlobalLeaderboard;
import flustix.fluxifyed.database.api.types.APIResponse;
import flustix.fluxifyed.database.api.types.APIRoute;
import flustix.fluxifyed.database.api.types.IRoute;

import java.util.HashMap;

@APIRoute(path = "/modules/xp/leaderboard/global")
public class GlobalLeaderboardRoute implements IRoute {
    public APIResponse execute(HttpExchange exchange, HashMap<String, String> params) {
        HashMap<String, String> query = QueryUtils.getQuery(exchange.getRequestURI().getQuery());

        int limit = query.containsKey("limit") ? Integer.parseInt(query.get("limit")) : 20;
        int offset = query.containsKey("offset") ? Integer.parseInt(query.get("offset")) : 0;

        return new APIResponse(200, "OK", new GlobalLeaderboard(limit, offset));
    }
}