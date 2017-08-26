package com.weight;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.weight.MorphiaConfiguration;
import com.mongodb.MongoClient;

public class MorphiaConfiguration {

	private String packageName = "com.weight.model";

	private static MorphiaConfiguration morphiaConfiguration = new MorphiaConfiguration();

	private Datastore datastore = null;

	private MorphiaConfiguration() {
		Morphia morphia = new Morphia();
		try {
			datastore = morphia.createDatastore(new MongoClient(), "kranthi");
			datastore.ensureIndexes();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Datastore getDatastore() {
		return datastore;
	}

	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public static MorphiaConfiguration getInstance() {
		return morphiaConfiguration;
	}
}
