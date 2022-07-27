package server.router.routes;

import java.util.Arrays;
import java.util.List;

public class AllRoutes {
  public static List<Route> getList() {
    return Arrays.asList(
            new SimpleGet(),
            new SimpleGetWithBody(),
            new HeadRequest()
    );
  }
}
