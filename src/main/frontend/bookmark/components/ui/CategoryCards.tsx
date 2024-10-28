import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { useState, useEffect } from 'react'
import Category from './Category'
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Bookmark, Search, User, Edit, Trash, Plus } from 'lucide-react'

interface CategoryProps {
    inUserId:number
    onStateChage:(newState:any) => void
}

export function CategoryCards({inUserId, onStateChage}: CategoryProps) {
    const baseUrl = 'http://localhost:8080/category'
    const [userId, setUserId] = useState<number>(inUserId);
    const [editingCategory, setEditingCategory] = useState<Category | null>(null);
    const [newCategoryName, setNewCategoryName] = useState('');
    const [categories, setCategories] = useState<Category[]>([]);

    useEffect(() => {
        readCategory();
    }, [])
    
    const createCategory = async () => {
        if (newCategoryName.trim()) {
          // 서버에 새로운 카테고리 추가 요청
          const newCategory = { "userId": userId, "title": newCategoryName.trim() }; // userId는 상황에 맞게 설정
          const response = await fetch(baseUrl, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newCategory),
          });
          const addedCategory = await response.json();
          setCategories([...categories, addedCategory]);
          setNewCategoryName('');
        }
    };

    const readCategory = async () => {
        try {
          const response = await fetch(baseUrl+'/'+userId);
          if (!response.ok) {
            throw new Error('카테고리 가져오기 실패');
          }
          const data: Category[] = await response.json();
          setCategories(data);
          // setSelectedCategory(2); 
        } catch (error) {
          console.error('카테고리 가져오기 오류:', error);
        } 
      };

      
      const deleteCategory = (categoryId : number) => {
        

      }
  

    return <Card>
    <CardHeader>
        <CardTitle>내 카테고리</CardTitle>
    </CardHeader>

    <CardContent>
        <ul className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
        {categories?.map((category) => (
            <li key={category.id} className="flex items-center space-x-2">
            <Button variant="outline" className="w-full justify-start" onClick={() => onStateChage(category.id)}>
                <Bookmark className="mr-2 h-4 w-4" />
                {category.title}
            </Button>
            <Button size="icon" variant="ghost" onClick={() => {
                onStateChage;
                // startEditing()
            }}>
                <Edit className="h-4 w-4" />
            </Button>
            <Button size="icon" variant="ghost" onClick={() => {
                onStateChage;
                deleteCategory(category.id);
            }}>
                <Trash className="h-4 w-4" />
            </Button>
            
            </li>
        ))}
        </ul>
        <div className="mt-4 flex space-x-2">
              <Input
                value={newCategoryName}
                onChange={(e) => setNewCategoryName(e.target.value)}
                placeholder="새 카테고리 이름"
                className="flex-grow"
              />
              <Button onClick={createCategory}>
                <Plus className="mr-2 h-4 w-4" />
                추가
              </Button>
        </div>
    </CardContent>
</Card>
}
export default CategoryCards;