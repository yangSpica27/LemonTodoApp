package spica.lemon.plan.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 用户
 */
@Entity
data class User(
  @PrimaryKey(autoGenerate = false)
  var id: Long = 0x01,
  var userName: String = "编辑您的名称",//名称
  var avatar: String = "default_avatar.jpg",//头像
  var emial: String = "", //电邮
  var identity: Int = 0x01 //身份
)

