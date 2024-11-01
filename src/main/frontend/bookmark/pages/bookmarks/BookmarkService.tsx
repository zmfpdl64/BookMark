import BookmarkItem from './BookmarkItem';

class BookmarkService {
    private baseUrl: string;

    constructor(baseUrl: string = 'http://localhost:8080/bookmark') {
        this.baseUrl = baseUrl;
    }

    async fetchBookmarkByuserIdAndCategoryId(userId: number, categoryId: number): Promise<BookmarkItem[]> {
        const response = await fetch(`${this.baseUrl}?userId=${userId}&categoryId=${categoryId}`);
        if (!response.ok) {
            throw new Error('북마크를 가져오는데 실패했습니다');
        }
        return response.json();
    }
}

export const bookmarkService = new BookmarkService();
