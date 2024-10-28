  "use client"

  import { useState, useEffect } from 'react'
  import { Bookmark, Router } from 'lucide-react'
  import { Button } from "@/components/ui/button"
  import Category from "./ui/Category" // 변경: Category.tsx 파일이 모듈로 내보내는지 확인 필요
  import Navbar from './ui/Navbar'
  import BookmarkSearch from './ui/BookmarkSearch'
  import { CategoryCards } from './ui/CategoryCards' // 변경: 기본 내보내기에서 명명된 내보내기로 수정
  import { bookmarkService } from '@/pages/bookmarks/BookmarkService'
  import BookmarkItem from 'pages/bookmarks/BookmarkItem'
  import BookmarkCards from './ui/BookmarkCards' // Bookmarks 컴포넌트 추가
  import Login from './ui/Login'
  
  // import { useHistory } from 'react-router-dom';

  export function MainPageComponent() {
    const [isLoggedIn, setIsLoggedIn] = useState<boolean>(false);
    // const [bookmarks, setBookmarks] = useState<BookmarkItem[]>([])
    const [selectedCategory, setSelectedCategory] = useState<number>(0) // 선택된 카테고리 ID 상태 추가
    const [categoryId, setCategoryId] = useState<number>(1);
    const userId = 1

    useEffect(() => {
      
      return () => {
        setCategoryId(1);
        setSelectedCategory(0);
      }
    }, []); // 빈 배열을 의존성 배열로 사용하여 컴포넌트 마운트 시 한 번만 실행
    
    
    
    const clickCategory = (categoryId: number) => {
      setCategoryId(categoryId); // 클릭한 categoryId로 상태 업데이트
      setSelectedCategory(2);
    };
    const backToOrigin = () => {
      setSelectedCategory(1);
    };
    const clickLogin = () => {
      setIsLoggedIn(true);
      setSelectedCategory(1);
    }
    const loginLogout = (isLoggedIn : boolean) => {
      setIsLoggedIn(isLoggedIn);
      if(isLoggedIn === false) {
        setSelectedCategory(0);
      }else{
        clickLogin()
        setSelectedCategory(1);
      }
    }

    return (
      <div className="min-h-screen bg-gray-50">
        <Navbar isLoggedIn={isLoggedIn} onStateChange={loginLogout} />
        <main className="container mx-auto px-4 py-8">
          <BookmarkSearch />
          {selectedCategory===0 && (
            <Login onStateChange={clickLogin} action="signin"/> // 변경: "siginin"을 "signin"으로 수정
          )}
          {selectedCategory===1 && ( //category에서 이벤트 발생시 처리하는 로직 추가 
            <CategoryCards inUserId={userId} onStateChage={clickCategory}/> 
          )}
          {selectedCategory===2 && (
            <>
              <Button variant="outline" className="w-full justify-start" onClick={backToOrigin}>
                뒤로가기
              </Button>
              <BookmarkCards userId={userId} categoryId={categoryId} />
            </>
          )}
        </main>
      </div>
    )
  }
