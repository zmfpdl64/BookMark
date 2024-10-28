import { useState, useEffect } from 'react';
import Image from 'next/image'; // Next.js의 Image 컴포넌트를 가져옵니다.
import Link from 'next/link'; // Next.js의 Link 컴포넌트를 가져옵니다.
import BookmarkItem from 'pages/bookmarks/BookmarkItem'
import BookmarkCard from 'components/ui/BookmarkCard'
import { ArrowLeft, Bookmark, ExternalLink, Edit, Trash2, Plus } from 'lucide-react'
import { bookmarkService } from '@/pages/bookmarks/BookmarkService';
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
    DialogFooter,
  } from "@/components/ui/dialog"


function BookmarkCards(userId:any, categoryId:any) {
    userId=Number("1")
    categoryId=Number("1")

    const [bookmarks, setBookmarks] = useState<BookmarkItem[]>([])
    const [loading, setLoading] = useState<boolean>(true)
    const [error, setError] = useState<string | null>(null)
    const [searchTerm] = useState("")
    const [editedBookmark, setEditedBookmark] = useState<BookmarkItem | null>(null); // 수정된 북마크 상태 추가
    const [viewMode, setViewMode] = useState<'card' | 'list'>('card')
    const [isCreateDialogOpen, setIsCreateDialogOpen] = useState(false)

    const createBookmark = async (bookmark: Omit<BookmarkItem, 'id'>): Promise<BookmarkItem> => {
        // 실제로는 서버에 POST 요청을 보내야 합니다.
        return { ...bookmark, id: Date.now() };
      }
      
      const updateBookmark = async (bookmark: BookmarkItem): Promise<BookmarkItem> => {
        // 실제로는 서버에 PUT 요청을 보내야 합니다.
        return bookmark;
      }
      
      const deleteBookmark = async (id: number): Promise<void> => {
        // 실제로는 서버에 DELETE 요청을 보내야 합니다.
        console.log(`Bookmark with id ${id} deleted`);
      }

    useEffect(() => {
    if (!userId || !categoryId) return
    getBookmarks()
  }, [userId, categoryId])
  

  const getBookmarks = async () => {
    try {
      console.log(userId, categoryId);
      setLoading(true)
      const data = await bookmarkService.fetchBookmarkByuserIdAndCategoryId(Number(userId), Number(categoryId)) // 메서드 이름 수정
      setBookmarks(data as unknown as BookmarkItem[]) // 타입 변환을 'unknown'을 통해 수행
    } catch (err) {
      setError('북마크를 불러오는 중 오류가 발생했습니다.')
      setBookmarks([])
      console.error(err);
    } finally {
      setLoading(false)
    }
  }
  const handleButtonClick = (link:string) => {
        window.open(link, "_blank", "noopener,noreferrer"); // 새 탭에서 링크 열기
    };

  const filteredBookmarks = bookmarks.filter(bookmark =>
    bookmark.title.toLowerCase().includes(searchTerm.toLowerCase())
  )

  if (loading) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <p className="text-xl font-semibold text-gray-600">로딩 중...</p>
      </div>
    )
  }

  if (error) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <p className="text-xl font-semibold text-red-600">{error}</p>
      </div>
    )
  }
    return (
        
        <div >
            <main className="container mx-auto px-4 py-8">
            
              <div className="flex flex-col items-end">
              <div className="inline-flex rounded-lg border border-gray-200 bg-white p-1">
                <button
                  className={`inline-flex items-center justify-center rounded-md px-3 py-2 text-sm font-medium ${
                    viewMode === 'card'
                      ? 'bg-gray-100 text-gray-900'
                      : 'text-gray-500 hover:text-gray-700'
                  }`}
                  onClick={() => setViewMode('card')}
                >
                  <svg
                    className="mr-2 h-4 w-4"
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth={2}
                      d="M4 6a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2H6a2 2 0 01-2-2V6zM14 6a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2h-2a2 2 0 01-2-2V6zM4 16a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2H6a2 2 0 01-2-2v-2zM14 16a2 2 0 012-2h2a2 2 0 012 2v2a2 2 0 01-2 2h-2a2 2 0 01-2-2v-2z"
                    />
                  </svg>
                  카드
                </button>
                <button
                  className={`inline-flex items-center justify-center rounded-md px-3 py-2 text-sm font-medium ${
                    viewMode === 'list'
                      ? 'bg-gray-100 text-gray-900'
                      : 'text-gray-500 hover:text-gray-700'
                  }`}
                  onClick={() => setViewMode('list')}
                >
                  <svg
                    className="mr-2 h-4 w-4"
                    fill="none"
                    stroke="currentColor"
                    viewBox="0 0 24 24"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth={2}
                      d="M4 6h16M4 10h16M4 14h16M4 18h16"
                    />
                  </svg>
                  리스트
                </button>
                <Button onClick={() => setIsCreateDialogOpen(true)} className="">
                  <Plus className="h-4 w-4 mr-2" />
                  새 북마크
                </Button>
                </div>
                </div>
              
                <h1 className="text-4xl font-bold text-gray-900 mb-8"> 북마크</h1>
                  <div className={viewMode === 'card' ? "grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6" : "space-y-4"}>
                    {bookmarks.map((bookmark) => (
                    <BookmarkCard 
                        key={bookmark.id} 
                        bookmark={bookmark} 
                        onUpdate={updateBookmark}
                        onDelete={deleteBookmark}
                    />
                    ))}
                  </div>
            </main>
            <div className={viewMode === 'card' ? "grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6" : "space-y-4"}>
          </div>
        </div>
    );
  }
  export default BookmarkCards;