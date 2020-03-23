package cn.dyaoming.outman.common;


import com.baomidou.mybatisplus.extension.activerecord.Model;

public abstract class BaseEntity<T extends Model<?>> extends Model<T> {

	private String isvalid;
}
