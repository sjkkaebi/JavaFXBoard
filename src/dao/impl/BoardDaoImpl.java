package dao.impl;

import ibatis.SqlMapConfig;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import vo.BoardVO;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import dao.BoardDao;

/**
 * @author 최민규
 * @version 1.0
 * 
 *          <p>
 *          파일명 : BoardDaoImpl.java <br/>
 *          설명 : 게시판 DAO 구현클래스 <br/>
 * 
 *          수정이력<br/>
 *          --------------------------------------------<br/>
 *          수정일자 |수정인|수정내용<br/>
 *          --------------------------------------------<br/>
 *          2017.01.24 최민규 최초생성<br/>
 *          --------------------------------------------<br/>
 *          </p>
 */

public class BoardDaoImpl implements BoardDao {

	// 싱글톤 패턴을 사용하기 위한 자신 클래스의 인스턴스
	private static BoardDaoImpl boardDaoImpl = new BoardDaoImpl();
	private SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();

	private BoardDaoImpl() {
		
	}

	public static BoardDaoImpl getInstance() {
		return boardDaoImpl;
	}

	@Override
	public List<BoardVO> selectBoardList(String boardTitle, String boardWriter) {
		List<BoardVO> resultList = null;
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("boardTitle", boardTitle);
		paramMap.put("boardWriter", boardWriter);
		
		try {
			resultList = sqlMap.queryForList("test.selectTest", paramMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	@Override
	public BoardVO selectBoard(String boardId) {
		BoardVO vo = null;
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("boardId", boardId);
		try {
			vo = (BoardVO) sqlMap.queryForObject("test.selectTest", paramMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;

	}

	@Override
	public void insertBoard(BoardVO vo) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("boardId", vo.getBoardId());
		paramMap.put("boardTitle", vo.getBoardTitle());
		paramMap.put("boardWriter", vo.getBoardWriter());
		paramMap.put("boardContent", vo.getBoardContent());

		try {
			sqlMap.insert("test.insertTest", paramMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateBoard(BoardVO vo) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("boardId", vo.getBoardId());
		paramMap.put("boardTitle", vo.getBoardTitle());
		paramMap.put("boardWriter", vo.getBoardWriter());
		paramMap.put("boardContent", vo.getBoardContent());

		try {
			sqlMap.insert("test.updateTest", paramMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBoard(String boardId) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("boardId", boardId);

		try {
			sqlMap.delete("test.deleteTest", paramMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
