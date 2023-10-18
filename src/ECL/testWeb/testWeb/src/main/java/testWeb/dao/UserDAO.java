 package testWeb.dao;

import testWeb.vo.*;

public interface UserDAO {
      public int insertByUserInfo (UserInfo userinfo) throws Exception;
      public int queryByUserInfo  (UserInfo userinfo) throws Exception;
      public int repeatByUserInfo(UserInfo userinfo) throws Exception;
}
