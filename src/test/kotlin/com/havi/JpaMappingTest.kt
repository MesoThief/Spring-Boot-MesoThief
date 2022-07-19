package com.havi

import com.havi.domain.Board
import com.havi.domain.User
import com.havi.domain.enums.BoardType
import com.havi.repository.BoardRepository
import com.havi.repository.UserRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class JpaMappingTest {
    private val testBoardTitle: String = "테스트"
    private val testEmail: String = "test@gmail.com"

    @Autowired
    var userRepository: UserRepository? = null

    @Autowired
    var boardRepository: BoardRepository? = null

    @Before
    fun init() {
        val testUser: User = userRepository!!.save(
            User(
                name = "testUser",
                password = "testPassword",
                email = testEmail,
            ),
        )

        boardRepository!!.save(
            Board(
                title = testBoardTitle,
                subTitle = "testSubTitle",
                content = "testContent",
                boardType = BoardType.FREE,
                user = testUser,
            ),
        )
    }

    @Test
    fun save_test() {
        val user = userRepository!!.findByEmail(testEmail)
        assertThat(user.name, `is`("testUser"))
        assertThat(user.password, `is`("testPassword"))
        assertThat(user.email, `is`(testEmail))

        val board = boardRepository!!.findByUser(user)
        assertThat(board.title, `is`(testBoardTitle))
        assertThat(board.subTitle, `is`("testSubTitle"))
        assertThat(board.content, `is`("testContent"))
        assertThat(board.boardType, `is`(BoardType.FREE))
    }
}
