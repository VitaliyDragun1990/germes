package org.itsimulator.germes.app.infra.cdi;

import org.glassfish.hk2.api.AnnotationLiteral;

/**
 * Special class that to be created for HK2 processor to support @Qualifier annotations
 * @author Vitaly Dragun
 *
 */
public class DBSourceInstance extends AnnotationLiteral<DBSource> implements DBSource {
	
	public static DBSource get() {
		return new DBSourceInstance();
	}

}
