package com.silberbullet.generate.meta.module.service

import io.kotest.core.spec.style.FreeSpec

class ModuleCommandServiceTest : FreeSpec({
    val commandService = ModuleCommandService()

    "[CREATE] 모듈 등록 시" - {
        "모듈 필수 정보가 전부 존재한다." - {
            "모듈 정보가 DB에 저장된다." {

            }

            "모듈이 플러그인 전용 Virtual File 로 생성된다." {

            }

            "모듈이 Generated File 로 생성된다." {

            }
        }

        "모듈 필수 정보가 하나라도 없으면 에러를 반환한다" - {

        }
    }
})
