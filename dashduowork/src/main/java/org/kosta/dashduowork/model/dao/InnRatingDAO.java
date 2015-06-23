package org.kosta.dashduowork.model.dao;

import org.kosta.dashduowork.model.vo.InnRatingVO;

public interface InnRatingDAO {

	int checkRatingTable(int innNo);
	void updateInnRating(InnRatingVO irv);
	void insertNewInnRating(InnRatingVO irv);

}
