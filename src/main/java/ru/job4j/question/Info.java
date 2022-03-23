package ru.job4j.question;

public class Info {

	private int added;
	private int changed;
	private int deleted;
	
	public Info(int added, int changed, int deleted) {
		super();
		this.added = added;
		this.changed = changed;
		this.deleted = deleted;
	}

	public int getAdded() {
		return added;
	}

	public void setAdded(int added) {
		this.added = added;
	}

	public int getChanged() {
		return changed;
	}

	public void setChanged(int changed) {
		this.changed = changed;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + added;
		result = prime * result + changed;
		result = prime * result + deleted;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Info other = (Info) obj;
		if (added != other.added) {
			return false;
		}
		if (changed != other.changed) {
			return false;
		}
		return deleted == other.deleted;
	}	

}
