package org.itsimulator.germes.app.model.entity.person;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.itsimulator.germes.app.model.entity.base.AbstractEntity;

/**
 * Entity that encapsulates the user of the application
 * 
 * @author Vitaly Dragun
 *
 */
@Table(name = "USER")
@Entity
@NamedQueries({
	@NamedQuery(name = User.QUERY_FIND_ALL, query = "from User"),
	@NamedQuery(name = User.QUERY_FIND_BY_USERNAME, query = "from User u WHERE u.userName = :username")
})
public class User extends AbstractEntity {
	public static final String QUERY_FIND_ALL = "User.findAll";
	public static final String QUERY_FIND_BY_USERNAME = "User.byUsername";

	/**
	 * Unique user name within the system
	 */
	private String userName;
	/**
	 * User password
	 */
	private String password;

	@NotNull
	@Size(min = 2, max = 24)
	@Column(name = "USERNAME", nullable = false, unique = true, length = 24)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@NotNull
	@Size(min = 2, max = 256)
	@Column(name = "PASSWORD", nullable = false, length = 256)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(userName, other.userName);
	}

}
